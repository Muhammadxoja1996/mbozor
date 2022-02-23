package uz.market.mbozor.dto.items;

import lombok.Data;

import javax.persistence.Column;

/**
 * Author: Muhammadxo'ja
 * Date: 23.02.2022
 * Time: 21:40
 */
@Data
public class ItemSuccessDto {
    @Column(name = "pay_date")
    private String payDate;
    @Column(name = "pay_period")
    private Integer payPeriod;
}
