package by.vss.exam.command.impl;

import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;

import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CCommand implements Command {
    TaskTestService service;
    TaskTest test;
    Long chatId;
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        service = new TaskTestService();
        test = service.getTestOrCreate(chatId);
        test.addUserAnswer("C");
        return new TestEngineCommand().execute(message, isCallback, callbackId);
    }
}
