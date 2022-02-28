package uz.market.mbozor.service.controllerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.market.mbozor.component.HttpSession;
import uz.market.mbozor.dto.ResponseDto;
import uz.market.mbozor.dto.auth.UserAuthDto;
import uz.market.mbozor.repository.UserAuthRepository;

import java.util.UUID;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:20
 */
@Service
public class AuthService {
    private final HttpSession httpSession;
    private final UserAuthRepository userAuthRepository;

    public AuthService(HttpSession httpSession, UserAuthRepository userAuthRepository) {
        this.httpSession = httpSession;
        this.userAuthRepository = userAuthRepository;
    }

    @Transactional
    public ResponseDto login(UserAuthDto userAuthDto) {
        if (userAuthRepository.existsByLoginAndPassword(userAuthDto.getLogin(), userAuthDto.getPassword())) {
            String token = UUID.randomUUID() + "&" + userAuthDto.getLogin();
            httpSession.setToken(token);
            userAuthRepository.updateToken(token, userAuthDto.getLogin());
            return new ResponseDto(0, "SUCCESS", null, null);
        }
        return new ResponseDto(1000, "ERROR", "User not found", null);
    }

    @Transactional
    public ResponseDto logout() {
        try {
            userAuthRepository.updateToken(null, httpSession.getToken().split("&")[1]);
            return new ResponseDto(0, "SUCCESS", null, null);
        } catch (Exception e) {
            return new ResponseDto(1000, "ERROR", "User not found", null);
        }
    }

    @Transactional
    public ResponseDto editPassword(String password) {
        userAuthRepository.updatePassword(password, httpSession.getToken().split("&")[1]);
        return new ResponseDto(0, "SUCCESS", null, null);
    }
}
