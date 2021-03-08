package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ManageMenuCommand implements Command {
    ManageSeanceService seanceService;
    ManageSeance manageSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);

        String menu =
                "*Меню уравления*\n\n" +
                        "*Create* - `Создание карт или тестов`\n" +
                        "*Edit* - `Редактирование `\n" +
                        "*Info* - `Информация`\n" +
                        "*Back* - `Вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Create", "Edit", "Control", "Back");
        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, menu);
        return new CommandResult(sendMessage);
    }
}
