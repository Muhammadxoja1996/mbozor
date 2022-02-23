package uz.market.mbozor.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.market.mbozor.entity.Item;
import uz.market.mbozor.entity.User;
import uz.market.mbozor.repository.ItemRepository;
import uz.market.mbozor.repository.UserRepository;
import uz.market.mbozor.utils.Utils;

import java.util.Date;
import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 21.02.2022
 * Time: 22:13
 */
@Service
public class NotificationService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final TelegramBotService telegramBotService;

    public NotificationService(ItemRepository itemRepository, UserRepository userRepository, TelegramBotService telegramBotService) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.telegramBotService = telegramBotService;
    }

    @Scheduled(fixedDelay = 3300000L)
    public void notificationLoop() {
        List<Item> items = itemRepository.findAll();
        String[] dateNow = Utils.simpleDateFormat.format(new Date()).split("\\.");
        items.forEach(item -> {
            try {
                String[] date = item.getPayDate().split("\\.");
                long result = (new Date().getTime() - Utils.simpleDateFormat.parse(item.getPayDate()).getTime()) / 1000 / 60 / 60 / 24;
                if (result > 20) {
                    int resultDay = Integer.parseInt(date[0]) - Integer.parseInt(dateNow[0]);
                    if (item.getNotificationTime() == 0) {
                        if (resultDay <= 3) {
                            User user = userRepository.findByUserName(item.getUserName());
                            telegramBotService.sendMessage(user.getTgId(), item.getUserName() + " to'lo'v kuningiz " + item.getPayDate());
                            item.setNotificationTime(23);
                        }
                    } else {
                        item.setNotificationTime(item.getNotificationTime() - 1);
                    }
                    itemRepository.save(item);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error sending message: [400] Bad Request: chat not found")){
                    System.out.println(item.getUserName()+
                            ": to'lo'v kuni aytilmadi\nTo'lo'v kuni: "+
                            item.getPayDate()+
                            "\nTovar: "+
                            item.getItemName());
                }
            }

        });

    }

}
