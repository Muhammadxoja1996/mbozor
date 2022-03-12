package uz.market.mbozor.controller.front;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.market.mbozor.dto.ContentPageableDto;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.users.UserDto;
import uz.market.mbozor.service.controllerService.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 12.03.2022
 * Time: 21:39
 */
@Controller
public class MenuController {

    private final UserService service;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MenuController(UserService service) {
        this.service = service;
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/users")
    public String users(Model model){
        ResponseDto responseDto = service.getAll(0,5);
        ContentPageableDto contentPageableDto =  objectMapper.convertValue(responseDto.getData(),ContentPageableDto.class);
        List<UserDto> userDtos = objectMapper.convertValue(contentPageableDto.getContent(),List.class);
        model.addAttribute("userDtos",userDtos);
        return "users";
    }
}
