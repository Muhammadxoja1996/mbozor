package uz.market.mbozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.mbozor.dto.ContentPageableDto;
import uz.market.mbozor.dto.ItemDto;
import uz.market.mbozor.dto.PageableDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.repository.ItemRepository;

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
            itemRepository.save(mapper.convertValue(dto, Item.class));
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }

    public ResponseDto deleteItem(Long itemId) {
        try{
            itemRepository.deleteById(itemId);
        } catch (Exception e) {
            return new ResponseDto(1, "ERROR", e.getMessage(), null);
        }
        return new ResponseDto(0, "SUCCESS", null, null);
    }
}
