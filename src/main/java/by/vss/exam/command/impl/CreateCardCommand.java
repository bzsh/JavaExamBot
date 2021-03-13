package by.vss.exam.command.impl;

import by.vss.exam.bean.User;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class CreateCardCommand implements Command {
    UserService userService;
    TaskTestService service;
    SendMessage sendMessage;
    InlineKeyboardMarkup markup;
    String startTestResponse;
    TaskTest test;
    Long chatId;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        userService = new UserService();
        service = new TaskTestService();
        test = service.getTestOrCreate(chatId);
        user = userService.getUser(chatId);

        startTestResponse = " *Меню создания учебных карточек* \n" +
                "выберите нужный тип карт:\n";

        List<String> buttonNames = Arrays.asList("< Java >", "< English >", "Выход");
        List<String> buttonQueries = Arrays.asList("create_java", "create_english", "Quit_create");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, startTestResponse);
        return new CommandResult(sendMessage);
    }
}
