package uz.market.mbozor.service.controllerService;

import org.springframework.stereotype.Service;
import uz.market.mbozor.component.HttpSession;
import uz.market.mbozor.dto.ResponseDto;

import java.util.UUID;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:20
 */
@Service
public class AuthService {
    private final HttpSession httpSession;

    public AuthService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public ResponseDto login(){
        httpSession.setToken(UUID.randomUUID().toString());
        return null;
    }

    public ResponseDto logout(){
        return null;
    }
}
