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

public class ExitCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String response = "Some exit message";
        Long chatId = message.getChatId();
//        Integer messageId = message.getMessageId();
        List<String> names = Arrays.asList("one", "two", "start", "four", "five");
        List<String> queries = Arrays.asList("/one", "/two", "/start", "/four", "/five");
        InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(names, queries);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, response);
        return new CommandResult(sendMessage);
    }
}
