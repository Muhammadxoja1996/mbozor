package uz.market.mbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.market.mbozor.entity.Item;

import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 16.02.2022
 * Time: 21:50
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByUserName(String userName);

    @Query("SELECT p.itemName FROM Item p")
    List<String> getAllItems();

    @Query("SELECT p.payDate , p.payPeriod FROM Item p WHERE p.id = :id")
    List<String> getPayDate(Long id);

    @Modifying
    @Query("UPDATE Item p SET p.payPeriod = :payPeriod , p.payDate = :payDate WHERE p.id = :id")
    void updateItem(Long id, String payDate, Integer payPeriod);
}
