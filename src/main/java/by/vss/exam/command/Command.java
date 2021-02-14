package by.vss.exam.command;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    CommandResult execute(Message message, boolean isCallback, String callbackId);
}
