package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.DeleteMessageCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class MainMenuCommand implements Command {
    ReplyKeyboardMarkup markup;
    String menuResponse;
    Integer messageId;
    Message message;
    String menu;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.message = message;
        chatId = message.getChatId();
        messageId = message.getMessageId();
        menu =
                "*Test* - `тестированиие`\n" +
                        "*Card* - `тренировка`\n" +
                        "*Manage* - `управление`\n" +
                        "*About* - `о программе`";

        List<String> buttonNames = Arrays.asList("Tеst", "Cаrd", "Mаnage", "Аbout");
        markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        return getResultOfCallback(isCallback, menu);
    }

    private CommandResult getResultOfCallback(boolean isCallback, String text) {
        if (isCallback) {
            menuResponse = menu;
            DeleteMessage deleteMessage = DeleteMessageCreator.createDeleteMessage(chatId, messageId);
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, text);
            return new CommandResult(deleteMessage, sendMessage);
        } else {
            String firstname = message.getFrom().getFirstName();
            String lastname = message.getFrom().getLastName();
            String greetings = "`Hello` *" + firstname + "* *" + lastname + "*\n";
            menuResponse = greetings + menu;
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, menuResponse);
            return new CommandResult(sendMessage);
        }
    }
}
