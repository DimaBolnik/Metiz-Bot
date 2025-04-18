package ru.bolnik.dispatcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.bolnik.dispatcher.controller.TelegramBotController;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBotController controller) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(controller);
        return telegramBotsApi;
    }
}
