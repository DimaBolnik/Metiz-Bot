package ru.bolnik.dispatcher.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

//Обрабатывает входящие обновления (Update) и может быть расширен для обработки разных типов команд или состояний.
@Component
public class TelegramMessageDispatcher {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBot.class);

//    Храним ссылку на TelegramBot и использует его для отправки ответов
    private TelegramBot telegramBot;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            logger.info("Получено сообщение: {} от пользователя с chatId: {}", messageText, chatId);

            // Отправляем ответ пользователю
            telegramBot.sendResponse(chatId, "Ваш запрос принят в обработку.");
        }
    }
}
