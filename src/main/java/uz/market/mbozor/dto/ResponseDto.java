package uz.market.mbozor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private Integer code;
    private String message;
    private String errMessage;
    private Object data;
}
