package uz.market.mbozor.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.repository.ItemRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 21.02.2022
 * Time: 22:13
 */
@Service
public class Notification {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private final ItemRepository itemRepository;

    public Notification(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Scheduled(fixedDelay = 3300000L)
    public void notificationLoop() {
        List<Item> items = itemRepository.findAll();
        String[] dateNow = simpleDateFormat.format(new Date()).split("\\.");
        items.forEach(item -> {
            try {
                String[] date = item.getPayDate().split("\\.");
                long result = (new Date().getTime() - simpleDateFormat.parse(item.getPayDate()).getTime()) / 1000 / 60 / 60 / 24;
                if (result > 20) {
                    int resultDay = Integer.parseInt(date[0]) - Integer.parseInt(dateNow[0]);
                    if (item.getNotificationTime() == 0) {
                        if (resultDay <= 3) {
                            System.out.println(item.getUserName() + " to'la " + item.getId());
                            item.setNotificationTime(23);
                        }
                    } else {
                        item.setNotificationTime(item.getNotificationTime() - 1);
                    }
                    itemRepository.save(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

    }

}
