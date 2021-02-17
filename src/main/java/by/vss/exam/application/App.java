package by.vss.exam.application;

import by.vss.exam.controller.ExamBotController;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class App {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new ExamBotController());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
