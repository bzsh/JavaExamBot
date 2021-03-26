package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class InfoCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String aboutMessage = "~*Info*~\n\n" +
                "`Sed do eiusmod tempor incididunt`\n" +
                "`ut labore et dolore magna aliqua.`" +
                "`Ut enim ad minim veniam, `\n\n" +
                "`quis nostrud exercitation ullamco`\n\n" +
                "`laboris nisi ut aliquip ex ea`\n\n" +
                "`commodo consequat.`";
        List<String> button = new ArrayList<>();
        button.add("Back to Menu");
        List<String> query = new ArrayList<>();
        query.add("Mаnage");
        InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(button, query);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(message.getChatId(), markup, aboutMessage);
        sendMessage.enableMarkdown(true);
        sendMessage.disableWebPagePreview();
        return new CommandResult(sendMessage);
    }
}
