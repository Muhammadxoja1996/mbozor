package uz.market.mbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:28
 */
@Entity
@Data
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sec")
    @SequenceGenerator(name = "user_sec", sequenceName = "user_sec", allocationSize = 1)
    private Long id;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_status")
    private Boolean userStatus;

    @Column(name = "tg_id")
    private Long tgId;

    @Column(name = "tg_number")
    private String tgNumber;

    private String comment;
}
