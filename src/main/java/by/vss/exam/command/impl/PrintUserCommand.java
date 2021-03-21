package by.vss.exam.command.impl;

import by.vss.exam.bean.user.User;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class PrintUserCommand implements Command {
    InlineKeyboardMarkup markup;
    Integer messageId;
    String response;
    UserService userService;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        userService = new UserService();
        response = getResponseStringFromUsers();

        List<String> buttonNames = Arrays.asList("Назад");
        List<String> buttonQueries = Arrays.asList("print_list");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, response);
        return new CommandResult(editMessageText);
    }

    private String getResponseStringFromUsers() {
        StringBuilder builder = new StringBuilder();
        List<User> users = userService.getAllUsers();
        builder.append("*USER*").
                append('/').append("*FIRST NAME*").
                append('/').append("*LAST NAME*").
                append('/').append("*ROLE*").
                append('/').append("*ID*").append('\n').append('\n');
        for (User u : users) {
            builder.append(u.getUserName()).
                    append('/').append(u.getFirstName()).
                    append('/').append(u.getLastName()).
                    append('/').append(u.getRole()).
                    append('/').append(u.getUserId()).append('\n');
        }
        return builder.toString();
    }
}
