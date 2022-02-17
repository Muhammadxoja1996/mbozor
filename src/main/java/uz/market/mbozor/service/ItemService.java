package uz.market.mbozor.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import uz.market.mbozor.dto.ItemDto;
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


    public ResponseDto getAll()  {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = null;
        try {
            itemDtos = mapper.convertValue(items, List.class);
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
        return new ResponseDto(0,"SUCCESS",null,itemDtos);

    }

    public ResponseDto getOne(Long id){
        try {
            return new ResponseDto(0,"SUCCESS",null,mapper.convertValue(itemRepository.findById(id),ItemDto.class));
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
    }

    public ResponseDto itemAdd(ItemDto dto){
        try {
            itemRepository.save(mapper.convertValue(dto,Item.class));
        } catch (Exception e){
            return new ResponseDto(1,"ERROR",e.getMessage(),null);
        }
        return new ResponseDto(0,"SUCCESS",null,null);
    }
}
