package uz.market.mbozor.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.market.mbozor.dto.items.ItemDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.service.controllerService.ItemService;

import java.io.File;

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

    @GetMapping("/get-all")
    public ResponseDto getAll(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "5") Integer size) {
        return service.getAll(page, size);
    }

    @GetMapping("/get-one/{id}")
    public ResponseDto getOne(@PathVariable("id") Long id) {
        return service.getOne(id);
    }

    @PostMapping("/add")
    public ResponseDto getOne(@RequestBody ItemDto itemDto) {
        return service.itemAdd(itemDto);
    }

    @GetMapping("/items-name")
    public ResponseDto getItemsName() {
        return service.getItemNames();
    }

    @PutMapping("/update")
    public ResponseDto update(@RequestBody Item item) {
        return service.update(item);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

    @GetMapping("/pay-success/{id}")
    public ResponseDto paySuccess(@PathVariable("id") Long id, @RequestParam Integer period) {
        return service.paySuccess(id, period);
    }

    @PostMapping("/file-upload")
    public ResponseDto fileUpload(@RequestBody MultipartFile multipartFile) {
        return service.fileUpload(multipartFile);
    }

    @GetMapping("/file-download")
    public ResponseDto fileDownload(@RequestParam String filePath) {
        return service.fileDownload(filePath);
    }
}
