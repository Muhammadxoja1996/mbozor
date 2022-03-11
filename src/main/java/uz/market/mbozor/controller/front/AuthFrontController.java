package uz.market.mbozor.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.market.mbozor.dto.auth.UserAuthDto;

/**
 * Author: Muhammadxo'ja
 * Date: 11.03.2022
 * Time: 14:46
 */
@Controller
@RequestMapping("/")
public class AuthFrontController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginModel",new UserAuthDto());
        return "login";
    }

    @GetMapping("/manager-panel")
    public String mainMenu(Model model){
        return "manager";
    }
}
