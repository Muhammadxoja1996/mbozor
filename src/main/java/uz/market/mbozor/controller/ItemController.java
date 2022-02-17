package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.*;
import uz.market.mbozor.dto.ItemDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.service.ItemService;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:27
 */
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }


    // page qo'shish kere
    @GetMapping("/get-all")
    public ResponseDto getAll(){
//    public List<UserDto> getAll(@RequestParam(defaultValue = "0") Integer page,
//                                @RequestParam(defaultValue = "5") Integer size) {
        return service.getAll();
    }

    @GetMapping("/get-one/{id}")
    public ResponseDto getOne(@PathVariable("id") Long id){
        return service.getOne(id);
    }

    @GetMapping("/add")
    public ResponseDto getOne(@RequestBody ItemDto itemDto){
        return service.itemAdd(itemDto);
    }
}
