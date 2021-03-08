package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class QuitCreateCardCommand implements Command {
    Long chatId;
    Integer messageId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();

        if (isCallback) {
            String response = "Спасибо за участие и пополнении\n" +
                    "коллекции карточек !";
            List<String> buttonNames = Arrays.asList("В меню");
            List<String> buttonQueries = Arrays.asList("Manage");
            InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            return new CommandResult(EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, response));
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }
}
