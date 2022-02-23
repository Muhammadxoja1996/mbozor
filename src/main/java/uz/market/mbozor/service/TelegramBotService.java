package uz.market.mbozor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


/**
 * Author: Muhammadxo'ja
 * Date: 23.02.2022
 * Time: 22:15
 */
@Configuration
@PropertySource("classpath:application.properties")
public class TelegramBotService extends TelegramLongPollingBot {

    @Value("${telegrambot.name}")
    private String NAME;
    @Value("${telegrambot.token}")
    private String TOKEN;

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Bean
    public void runBot()  {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBotService());
        } catch (TelegramApiException ignored) {}
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getId());
    }

    public void sendMessage(Long id, String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(id.toString());
        execute(sendMessage);
    }

}
