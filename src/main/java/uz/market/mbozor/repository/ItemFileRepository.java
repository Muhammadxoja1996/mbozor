package uz.market.mbozor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.market.mbozor.entity.ItemFiles;

/**
 * Author: Muhammadxo'ja
 * Date: 02.03.2022
 * Time: 21:43
 */
@Repository
public interface ItemFileRepository extends JpaRepository<ItemFiles, Long> {
}
