package uz.market.mbozor.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Status code")
    private Integer code;
    @ApiModelProperty(notes = "Response message")
    private String message;
    @ApiModelProperty(notes = "Error message")
    private String errMessage;
    @ApiModelProperty(value = "Response data")
    private Object data;
}
