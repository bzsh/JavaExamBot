package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.FrameCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public class ExampleCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String text = "Всегда ли исполняется блок finally?";

        String result = "`" + FrameCreator.createFrameStringMessage(text, "║") + "`";
        SendMessage sendMessage = SendMessageCreator.createSendMessage(message.getChatId(), result);
        sendMessage.enableMarkdownV2(false);
        sendMessage.enableMarkdown(true);

        sendMessage.disableWebPagePreview();
        return new CommandResult(sendMessage);
    }
}
