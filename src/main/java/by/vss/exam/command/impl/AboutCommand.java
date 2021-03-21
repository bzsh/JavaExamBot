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

public class AboutCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String aboutMessage = "~*JavaExamBot*~\n\n" +
                "`This bot was created for studying and testing knowledge.`\n\n" +
                "`Use` *Test* `mode to check your knowledge`.\n" +
                "`Use` *Card* `mode to studying`.\n" +
                "`Use` *Manage* `mode to add new TestTasks or Cards, `" +
                "`you can also type comments for each task.`\n\n" +
                "`For any questions message` [here](tg://user?id=441501611)\n\n" +
                "*Source code:* [github.com/bzsh/JavaExamBot](https://github.com/bzsh/JavaExamBot)\n";
        List<String> button = new ArrayList<>();
        button.add("Back to Main Menu");
        List<String> query = new ArrayList<>();
        query.add("main_menu");
        InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(button, query);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(message.getChatId(), markup, aboutMessage);
        sendMessage.enableMarkdown(true);
        sendMessage.disableWebPagePreview();
        return new CommandResult(sendMessage);
    }
}
