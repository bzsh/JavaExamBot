package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ViewManageSeanceWarningMenu implements Command {
    ManageSeanceService seanceService;
    InlineKeyboardMarkup markup;
    ManageSeance manageSeance;
    String response;
    Long chatId;
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);
        return viewWarningMenu(message);
    }

    private CommandResult viewWarningMenu(Message message) {
        response = " У вас не закончен сеанс: " + manageSeance.getManageType().getStringOf() + "! \n" +
                "Нажмите: \n" +
                "вернуться - что бы вернуться к сеансу.\n" +
                "выход -  что бы сбросить сеанс и выйти.";
        List<String> buttonNames = Arrays.asList("Вернуться", "Выход");
        List<String> buttonQueries = Arrays.asList("MANAGE_ENGINE_COMMAND", "RESET_SEANCE_COMMAND");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, message.getMessageId(), markup, response);
        return new CommandResult(editMessageText);
    }
}
