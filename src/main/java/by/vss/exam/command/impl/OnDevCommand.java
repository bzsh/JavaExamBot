package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class OnDevCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String response = "*Извините, раздел недоступен \nтак как находится в разработке!*";
        Long chatId = message.getChatId();
        List<String> names = Arrays.asList("Вернуться");
        List<String> queries = Arrays.asList("Back");
        InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(names, queries);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, response);
        return new CommandResult(sendMessage);
    }
}
