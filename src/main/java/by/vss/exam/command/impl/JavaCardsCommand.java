package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import org.telegram.telegrambots.meta.api.objects.Message;

public class JavaCardsCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }
}
