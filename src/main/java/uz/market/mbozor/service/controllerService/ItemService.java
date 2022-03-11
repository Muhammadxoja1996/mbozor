package uz.market.mbozor.service.controllerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.market.mbozor.dto.ContentPageableDto;
import uz.market.mbozor.dto.items.ItemDto;
import uz.market.mbozor.dto.PageableDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.entity.ItemFiles;
import uz.market.mbozor.repository.ItemFileRepository;
import uz.market.mbozor.repository.ItemRepository;
import uz.market.mbozor.utils.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:51
 */
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemFileRepository itemFileRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public ItemService(ItemRepository itemRepository, ItemFileRepository itemFileRepository) {
        this.itemRepository = itemRepository;
        this.itemFileRepository = itemFileRepository;
    }

    public List<ItemDto> getByUserName(String userName) {
        try {
            return mapper.convertValue(itemRepository.findAllByUserName(userName), List.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public ResponseDto getItemNames() {
        List<String> stringList;
        try {
            stringList = itemRepository.getAllItems();
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, stringList);
    }

    public ResponseDto getAll(Integer page, Integer size) {
        if (size > 20) {
            size = 20;
        }
        Page<Item> items = itemRepository.findAll(PageRequest.of(page, size));
        ContentPageableDto contentPageableDto = new ContentPageableDto();
        try {
            contentPageableDto.setPageable(new PageableDto(items.getTotalElements(), items.getTotalPages(), size, page));
            contentPageableDto.setContent(mapper.convertValue(items.getContent(), List.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, contentPageableDto);

    }

    public ResponseDto getOne(Long id) {
        try {
            return new ResponseDto(0, "SUCCESS", null, mapper.convertValue(itemRepository.findById(id), ItemDto.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

    public ResponseDto itemAdd(ItemDto dto) {
        try {
            dto.setPayDate(Utils.simpleDateFormat.format(new Date(System.currentTimeMillis())));
            itemRepository.save(mapper.convertValue(dto, Item.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }

    public ResponseDto update(Item item) {
        try {
            itemRepository.save(item);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }

    public ResponseDto delete(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }

    @Transactional
    public ResponseDto paySuccess(Long id, Integer period) {
        try {
            List<String> item = itemRepository.getPayDate(id);
            int payPeriodResult = Integer.parseInt(item.get(0).split(",")[1]);
            if (payPeriodResult >= period) {
                payPeriodResult = payPeriodResult - period;
            } else {
                return new ResponseDto(1, "ERROR", "Period > period from DB", null);
            }
            String[] date = item.get(0).split(",")[0].split("\\.");
            String year = date[2];
            String month = (Integer.parseInt(date[1]) + period) + "";
            if (Integer.parseInt(month) > 12) {
                month = (Integer.parseInt(month) - 12) + "";
                year = (Integer.parseInt(year) + 1) + "";
            }
            if (month.length() == 1) {
                month = "0" + month;
            }
            String payDate = date[0] + "." + month + "." + year;
            itemRepository.updateItem(id, payDate, payPeriodResult);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }

    public ResponseDto fileUpload(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String path = "";
            if (multipartFile.getContentType().split("/")[0].equals("image")) {
                path = "files/image/" + multipartFile.getOriginalFilename();
            } else if (multipartFile.getContentType().split("/")[0].equals("video")) {
                path = "files/video/" + multipartFile.getOriginalFilename();
            } else {
                path = "files/others/" + multipartFile.getOriginalFilename();
            }
            System.out.println(multipartFile.getContentType());

            OutputStream outputStream = new FileOutputStream(path);
            outputStream.write(inputStream.readAllBytes());
            outputStream.close();
            itemFileRepository.save(new ItemFiles(null, multipartFile.getOriginalFilename(),
                    multipartFile.getContentType().split("/")[0],
                    multipartFile.getContentType().split("/")[1],
                    multipartFile.getSize()));

            return new ResponseDto(0, "SUCCESS", null, null);
        } catch (IOException e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

    public ResponseDto fileDownload(String filePath) {
        try {
            File file = new File(filePath);
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
            return new ResponseDto(0, "SUCCESS", null, new String(encoded, StandardCharsets.UTF_8));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

    public ResponseDto createItemExcel(MultipartFile multipartFile) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            InputStream inputStream = multipartFile.getInputStream();
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < rows + 1; i++) {
                Item item = new Item();
                XSSFRow row = sheet.getRow(i);
                if (row.getCell(0) != null) {
                    item.setComment(row.getCell(0).toString());
                }
                if (row.getCell(1) != null) {
                    item.setDiscount(Integer.parseInt(row.getCell(1).toString()));
                }
                item.setFirstPay((int) Double.parseDouble(row.getCell(2).toString()));
                item.setItemName(row.getCell(3).toString());
                item.setItemPrice((int) Double.parseDouble(row.getCell(4).toString()));
                item.setPayDate(simpleDateFormat.format(row.getCell(5).getDateCellValue()));
                item.setPayOfMonth((int) Double.parseDouble(row.getCell(6).toString()));
                item.setUserName(row.getCell(7).toString());
                item.setPayPeriod((int) Double.parseDouble(row.getCell(8).toString()));
                item.setNotificationTime((int) Double.parseDouble(row.getCell(9).toString()));
                itemRepository.save(item);
            }
            return new ResponseDto(0, "SUCCESS", null, null);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }

    public ResponseDto excelExample() {
        try {
            ClassPathResource classPathResource = new ClassPathResource("static/item.xlsx");
            byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(classPathResource.getFile()));
            String result = "data:file;base64," + new String(encoded, StandardCharsets.UTF_8);
            return new ResponseDto(0, "SUCCESS", null, result);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
    }
}
