package by.vss.exam.command.impl;

import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ControlCommand implements Command {
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

        if (user.getRole().equals(UserRole.USER)) {
            startTestResponse = "Здравствуй дорогой мой друг 🧙!";
            List<String> buttonNames = Arrays.asList("📜", "📝", "💾", "🚪");
            List<String> buttonQueries = Arrays.asList("Log_admin_command", "Edit_admin_command", "Save_admin_command", "Quit_create");
            markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(id, markup, startTestResponse);
            return new CommandResult(sendMessage);
        } else {
            startTestResponse = "Извините, 👾 данный раздел\n" +
                    "доступен только 🛠 администраторам!";
            List<String> buttonNames = Arrays.asList("Выход");
            List<String> buttonQueries = Arrays.asList("Manage");
            markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(id, markup, startTestResponse);
            return new CommandResult(sendMessage);
        }
    }
}
