package by.vss.exam.command.impl;

import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class EndTaskTestCommand implements Command {
    TaskTestService service;
    Integer messageId;
    TaskTest test;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        service = new TaskTestService();
        test = service.getTestOrCreate(chatId);
        service.doTestAsNew(test);
        if (isCallback) {
           return viewFinishMenu();
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }

    private CommandResult viewFinishMenu() {
        String text = "Тест завершен !\n" +
                "Результат сохранен, спасибо !\n";
        List<String> buttonNames = Arrays.asList("К меню");
        List<String> buttonQueries = Arrays.asList("main_menu");
       InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        return new CommandResult(EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, text));
    }
}
