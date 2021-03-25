package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ViewCreateSeanceWarnMenuCommand implements Command {
    CreateCardSeanceService seanceService;
    InlineKeyboardMarkup markup;
    CreateCardSeance createCardSeance;
    String response;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);
        return viewWarningMenu(isCallback, message);
    }

    private CommandResult viewWarningMenu(boolean isCallBack, Message message) {
        response = " У вас не закончен сеанс: " + createCardSeance.getCreateCardType().getStringOf() + "! \n" +
                "Нажмите: \n" +
                "вернуться - что бы вернуться к сеансу.\n" +
                "выход -  что бы сбросить сеанс и выйти.";
        List<String> buttonNames = Arrays.asList("Вернуться", "Выход");
        List<String> buttonQueries = Arrays.asList("CREATE_ENGINE_COMMAND", "RESET_SEANCE_COMMAND");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        return getResultOfCallback(isCallBack, message, response);
    }

    private CommandResult getResultOfCallback(boolean isCallback, Message message, String text) {
        if (isCallback) {
            EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, message.getMessageId(), markup, text);
            return new CommandResult(editMessageText);
        } else {
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, text);
            return new CommandResult(sendMessage);
        }
    }
}
