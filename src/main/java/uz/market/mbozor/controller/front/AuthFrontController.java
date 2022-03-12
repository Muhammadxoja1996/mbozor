package uz.market.mbozor.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.market.mbozor.dto.auth.UserAuthDto;

/**
 * Author: Muhammadxo'ja
 * Date: 12.03.2022
 * Time: 21:09
 */
@Controller
public class AuthFrontController {

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginObj",new UserAuthDto());
        return "login";
    }

}
