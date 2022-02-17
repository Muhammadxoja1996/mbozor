package uz.market.mbozor.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

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
//    private List<ItemDto> items;

}
