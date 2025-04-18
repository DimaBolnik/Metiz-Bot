package ru.bolnik.dispatcher.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBotController extends TelegramLongPollingBot {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotController.class);

    public TelegramBotController(@Value("${telegram.bot.token}")String botToken) {
        super(botToken);
    }

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            logger.info("Получено сообщение: {} от пользователя с chatId: {}", messageText, chatId);

            // Отправляем ответ пользователю
            sendResponse(chatId, "Ваш запрос принят в обработку.");
        }
    }

    private void sendResponse(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения: ", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

}