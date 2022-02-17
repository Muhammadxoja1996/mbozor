package uz.market.mbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.market.mbozor.entity.Item;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:50
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
