package uz.market.mbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.market.mbozor.entity.User;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:49
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
