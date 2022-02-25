package uz.market.mbozor.component;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:15
 */
@Data
@SessionScope
@Component
public class HttpSession {
    private String token;
}
