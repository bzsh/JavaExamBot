package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.DeleteMessageCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ManageMenuCommand implements Command {
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    ReplyKeyboardMarkup markup;
    Integer messageId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);

        String menu =
                "*Меню уравления*\n\n" +
                        "*Create* - `Создание карт`\n" +
                        "*Info* - `Информация`\n" +
                        "*Edit* - `Редактирование карт`\n" +
                        "*Back* - `Вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Creаte", "Infо", "Еdit", "Bаck");
        markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        return getResultOfCallback(isCallback, menu);
    }

    private CommandResult getResultOfCallback(boolean isCallback, String text) {
        if (isCallback) {
            DeleteMessage deleteMessage = DeleteMessageCreator.createDeleteMessage(chatId, messageId);
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, text);
            return new CommandResult(deleteMessage, sendMessage);
        } else {
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, text);
            return new CommandResult(sendMessage);
        }
    }
}
