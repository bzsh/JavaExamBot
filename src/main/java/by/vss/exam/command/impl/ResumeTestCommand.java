package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ResumeTestCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        return new TestCommand().execute(message, isCallback, callbackId);
    }
}
