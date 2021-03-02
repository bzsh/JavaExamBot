package by.vss.exam.command.impl;

import by.vss.exam.bean.User;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TestCommand implements Command {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final int NUMBER_OF_TASKS_IN_TEST = Integer.parseInt(bundle.getString(ConstantHolder.NUM_OF_TASKS));
    UserService userService;
    TaskTestService service;
    SendMessage sendMessage;
    InlineKeyboardMarkup markup;
    String startTestResponse;
    TaskTest test;
    Long id;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        id = message.getChatId();
        userService = new UserService();
        service = new TaskTestService();
        test = service.getTestOrCreate(id);
        user = userService.getUser(id);
        if (!test.isActive() && test.isNew()) {
            startTestResponse = "Тест на знание языка Java. " +
                    "В тесте " + NUMBER_OF_TASKS_IN_TEST + " вопросов. " +
                    "Для успешного прохождения " +
                    "теста, нужно ответить верно " +
                    "на минимум 70% вопросов. " +
                    "Удачи ! ";
            List<String> buttonNames = Arrays.asList("< Начать тест >", "< Назад к меню >");
            List<String> buttonQueries = Arrays.asList("resume_test", "main_menu");
            markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(id, markup, startTestResponse);
            return new CommandResult(sendMessage);
        }
        return new TestEngineCommand().execute(message, isCallback, callbackId);
    }
}
