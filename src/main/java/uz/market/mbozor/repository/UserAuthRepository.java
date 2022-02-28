package uz.market.mbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.market.mbozor.entity.UserAuth;

/**
 * Author: Muhammadxo'ja
 * Date: 27.02.2022
 * Time: 21:24
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {


    @Modifying
    @Query("UPDATE UserAuth p SET p.password = :password WHERE p.login = :login")
    void updatePassword(@Param("password") String password, @Param("login") String login);

    @Modifying
    @Query("UPDATE UserAuth p SET p.token = :token WHERE p.login = :login")
    void updateToken(@Param("token") String token, @Param("login") String login);

    boolean existsByLoginAndPassword(String login, String password);

    boolean existsByToken(String token);
}
