package uz.market.mbozor.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.market.mbozor.entity.UserAuth;
import uz.market.mbozor.repository.UserAuthRepository;

/**
 * Author: Muhammadxo'ja
 * Date: 27.02.2022
 * Time: 22:33
 */
@Component
public class AdminCreator implements CommandLineRunner {

    private final UserAuthRepository userAuthRepository;

    public AdminCreator(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public void run(String...args) {
        try {
            UserAuth userAuth = new UserAuth();
            userAuth.setLogin("admin");
            userAuth.setPassword("123456");
            userAuthRepository.save(userAuth);
        } catch (Exception ignored){}
    }
}
