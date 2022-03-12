package uz.market.mbozor.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.auth.UserAuthDto;
import uz.market.mbozor.service.controllerService.AuthService;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:18
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public RedirectView login(@ModelAttribute UserAuthDto userAuthDto) {
        authService.login(userAuthDto);
        return new RedirectView("/main");
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