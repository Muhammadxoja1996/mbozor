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

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping("/get-all")
    public ResponseDto getAll(@RequestParam(defaultValue = "0")  Integer page,
                                @RequestParam(defaultValue = "5") Integer size) {
        return userService.getAll(page, size);
    }

    @GetMapping("/get-one/{userName}")
    public ResponseDto addUser(@PathVariable("userName") String userName){
        return userService.getOne(userName);
    }

    @PostMapping("/add")
    public ResponseDto addUser(@RequestBody UserDto userDto){
        return userService.userAdd(userDto);
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseDto deleteStudent(@PathVariable("userId") Long userId ) {
        return userService.deleteStudent(userId);
    }

    @PutMapping(path = "/update/{userId}")
    public void updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Boolean userStatus,
            @RequestParam(required = false) Long tgId,
            @RequestParam(required = false) String tgNumber,
            @RequestParam(required = false) String comment){
        userService.updateUser(userId, userName, phoneNumber, userStatus, tgId, tgNumber, comment);
    }
}
