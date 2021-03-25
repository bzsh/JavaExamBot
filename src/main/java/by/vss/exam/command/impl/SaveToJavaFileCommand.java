package by.vss.exam.command.impl;

import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.repository.JavaCardRepository;
import by.vss.exam.service.UserService;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SaveToJavaFileCommand implements Command {
    UserService userService;
    Long chatId;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        userService = new UserService();
        user = userService.getUser(chatId);

        if (user.getRole().equals(UserRole.ADMIN)) {
            JavaCardRepository repository = JavaCardRepository.getInstance();
            repository.saveAllToFile();

            AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
            callbackQuery.setShowAlert(false);
            callbackQuery.setCallbackQueryId(callbackId);
            callbackQuery.setText("Сохранено !");
            return new CommandResult(callbackQuery);
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }
}
