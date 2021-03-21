package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class PrintListCommand implements Command {
    InlineKeyboardMarkup markup;
    Integer messageId;
    String response;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();

        response = " *Меню просмотра списков данных* \n" +
                "выберите нужный тип:\n";

        List<String> buttonNames = Arrays.asList(" Users ", " Java ", " English ", " Выход ");
        List<String> buttonQueries = Arrays.asList("print_user","print_java", "print_english", "ЕDIT");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, response);
        return new CommandResult(editMessageText);
    }
}
