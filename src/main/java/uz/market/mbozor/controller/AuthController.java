package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.market.mbozor.dto.ResponseDto;
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
    public ResponseDto login(){
        return authService.login();
    }

    @GetMapping("logout")
    public ResponseDto logout(){
        return authService.logout();
    }
}
