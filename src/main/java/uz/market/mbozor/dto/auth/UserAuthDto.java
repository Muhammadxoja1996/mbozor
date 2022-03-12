package uz.market.mbozor.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Muhammadxo'ja
 * Date: 27.02.2022
 * Time: 21:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {
    private String login;
    private String password;
}
