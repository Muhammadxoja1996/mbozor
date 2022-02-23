package uz.market.mbozor.dto.users;

import lombok.Data;
import uz.market.mbozor.dto.items.ItemDto;
import uz.market.mbozor.entity.User;

import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 19.02.2022
 * Time: 21:07
 */
@Data
public class UserItemsDto {

    private String userName;
    private String phoneNumber;
    private Boolean userStatus;
    private Long tgId;
    private String tgNumber;
    private String comment;
    private List<ItemDto> items;

    public UserItemsDto(User user) {
        this.userName = user.getUserName();
        this.phoneNumber = user.getPhoneNumber();
        this.userStatus = user.getUserStatus();
        this.tgId = user.getTgId();
        this.tgNumber = user.getTgNumber();
        this.comment = user.getComment();
    }
}
