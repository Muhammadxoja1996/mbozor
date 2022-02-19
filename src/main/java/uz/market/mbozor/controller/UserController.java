package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.*;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.UserDto;
import uz.market.mbozor.service.UserService;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:26
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/get-all")
    public ResponseDto getAll(@RequestParam(defaultValue = "0")  Integer page,
                                @RequestParam(defaultValue = "5") Integer size) {
        return service.getAll(page, size);
    }

    @GetMapping("/get-one/{userName}")
    public ResponseDto getOne(@PathVariable("userName") String userName){
        return service.getOne(userName);
    }

    @PostMapping("/add")
    public ResponseDto getOne(@RequestBody UserDto userDto){
        return service.userAdd(userDto);
    }
}
