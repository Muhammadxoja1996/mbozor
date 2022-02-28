package uz.market.mbozor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Author: Muhammadxo'ja
 * Date: 27.02.2022
 * Time: 21:21
 */
@Entity
@Table(name = "user_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_auth_sec")
    @SequenceGenerator(name = "user_auth_sec", sequenceName = "user_auth_sec", allocationSize = 1)
    private Long id;
    @Column(name = "login", unique = true)
    private String login;
    private String password;
    private String token;
}
