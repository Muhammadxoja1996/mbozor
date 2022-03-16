package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.users.UserDto;
import uz.market.mbozor.service.controllerService.UserService;

import java.util.Objects;

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

    @GetMapping(value = "/get-all")
    public ResponseDto getAll(@RequestParam(defaultValue = "0") Integer page,
                              @RequestParam(defaultValue = "5") Integer size) {
        return service.getAll(page, size);
    }

    @GetMapping("/get-one/{userName}")
    public ResponseDto getOne(@PathVariable("userName") String userName) {
        return service.getOne(userName);
    }

    @PostMapping("/add")
    public RedirectView getOne(@ModelAttribute UserDto userDto) {
        if (!Objects.equals(userDto.getUserName().trim(), "") && !Objects.equals(userDto.getPhoneNumber().trim(), "")) {
            service.userAdd(userDto);
        }
        return new RedirectView("/users");
    }

    @PostMapping("/update")
    public RedirectView update(@ModelAttribute UserDto userDto) {
        service.update(userDto);
        return new RedirectView("/users");
    }

    @GetMapping("/delete/{userName}")
    public RedirectView delete(@PathVariable("userName") String userName) {
        service.delete(userName);
        return new RedirectView("/users");
    }
}
