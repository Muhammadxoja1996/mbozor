package uz.market.mbozor.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import uz.market.mbozor.entity.User;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:52
 */
@Data
@JsonIgnoreProperties
public class UserDto {

    private String userName;
    private String phoneNumber;
    private Boolean userStatus;
    private Long tgId;
    private String tgNumber;
    private String comment;

    public UserDto(User user) {
        this.userName = user.getUserName();
        this.phoneNumber = user.getPhoneNumber();
        this.userStatus = user.getUserStatus();
        this.tgId = user.getTgId();
        this.tgNumber = user.getTgNumber();
        this.comment = user.getComment();
    }

    public UserDto() {
    }
}
