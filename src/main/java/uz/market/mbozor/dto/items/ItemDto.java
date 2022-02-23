package uz.market.mbozor.dto.items;

import lombok.Data;

import javax.persistence.Column;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:53
 */
@Data
public class ItemDto {
    private Long id;
    private String userName;
    private String itemName;
    private Integer itemPrice;
    private Integer firstPay;
    private Integer payOfMonth;
    private String payDate;
    private Integer payPeriod;
    private Integer discount;
    private String comment;
}
