package uz.market.mbozor.service.controllerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.mbozor.dto.ContentPageableDto;
import uz.market.mbozor.dto.items.ItemDto;
import uz.market.mbozor.dto.PageableDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.items.ItemSuccessDto;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.repository.ItemRepository;
import uz.market.mbozor.utils.Utils;

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
    private final ObjectMapper mapper = new ObjectMapper();

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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
            if (Integer.parseInt(month)  > 12) {
                month = (Integer.parseInt(month)-12) + "";
                year = (Integer.parseInt(year)+1)+"";
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
}
