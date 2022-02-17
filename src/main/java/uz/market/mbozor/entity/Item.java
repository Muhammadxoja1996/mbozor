package uz.market.mbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:37
 */
@Entity
@Data
@Table(name = "item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "item_sec")
    @SequenceGenerator(name = "item_sec", sequenceName = "item_sec", allocationSize = 1)
    private Long id;

    @Column(name = "user_name", nullable = true)
    private String userName;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Integer itemPrice;

    @Column(name = "first_pay")
    private Integer firstPay;

    @Column(name = "pay_of_month")
    private Integer payOfMonth;

    @Column(name = "pay_date")
    private String payDate;

    private Integer discount;

    private String comment;
}
