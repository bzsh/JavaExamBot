package by.vss.exam.command.impl;

import by.vss.exam.bean.User;
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

public class EditCommand implements Command {
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

        startTestResponse = " *Меню редактирования учебных карточек* \n" +
                "Вы можете редактировать \n" +
                "только созданные вами карточки \n" +
                "выберите нужный тип карт: \n";

        List<String> buttonNames = Arrays.asList("< Java >", "< English >", "Выход");
        List<String> buttonQueries = Arrays.asList("Edit_java", "Edit_english", "Quit_create");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(id, markup, startTestResponse);
        return new CommandResult(sendMessage);
    }
}
