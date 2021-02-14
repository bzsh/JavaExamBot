package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.DeleteMessageCreator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class EmptyCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        return new CommandResult(DeleteMessageCreator.createDeleteMessage(message.getChatId(), message.getMessageId()));
    }
}
