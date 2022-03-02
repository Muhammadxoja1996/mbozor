package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.*;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.auth.UserAuthDto;
import uz.market.mbozor.service.controllerService.AuthService;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:18
 */
@RestController
@RequestMapping("/")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseDto login(@RequestBody UserAuthDto userAuthDto) {
        return authService.login(userAuthDto);
    }

    @GetMapping("logout")
    public ResponseDto logout() {
        return authService.logout();
    }

    @PostMapping("edit-password")
    public ResponseDto editPassword(@RequestParam String password) {
        return authService.editPassword(password);
    }
}
