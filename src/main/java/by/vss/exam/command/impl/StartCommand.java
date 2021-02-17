package by.vss.exam.command.impl;

import by.vss.exam.bean.User;
import by.vss.exam.bean.role.UserRole;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;


public class StartCommand implements Command {
    UserService userService;
    TaskTestService service;
    TaskTest test;
    Long id;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        id = message.getChatId();
        userService = new UserService();
        service = new TaskTestService();
        test = service.getTestOrCreate(id);
        String startResponse;
        SendMessage sendMessage;
        String firstname = message.getFrom().getFirstName();
        String lastname = message.getFrom().getLastName();
        String userName = message.getFrom().getUserName();

        user = userService.getUserOrCreate(id, firstname, lastname, userName, UserRole.USER);

        if (isTestActiveOrExist()) {
            startResponse = firstname + " " + lastname + " У вас имеется незавершенный тест, хотите вернуться к тестированию?";
            List<String> buttonNames = Arrays.asList("< к тесту >", "< к меню >");
            List<String> buttonQueries = Arrays.asList("resume_test", "main_menu");
            InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(id, markup, startResponse);
        } else {
            return new MainMenuCommand().execute(message, isCallback, callbackId);
        }
        return new CommandResult(sendMessage);
    }
    private boolean isTestActiveOrExist() {
        return service.containsTest(id) && test.isActive();
    }
}
