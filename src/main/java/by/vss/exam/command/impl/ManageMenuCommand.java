package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ManageMenuCommand implements Command {
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);

        String menu =
                "*Меню уравления*\n\n" +
                        "*Create* - `Создание карт`\n" +
                        "*Info* - `Информация`\n" +
                        "*Edit* - `Редактирование карт`\n" +
                        "*Back* - `Вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Creаte", "Infо", "Еdit", "Bаck");
        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, menu);
        return new CommandResult(sendMessage);
    }
}
