package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.bean.manage.editCard.EditCardType;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class EditUserCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editSeance;
    UserService userService;
    Long chatId;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        userService = new UserService();
        user = userService.getUser(chatId);
        editSeanceService = new EditCardSeanceService();
        editSeance = editSeanceService.getEditCardSeanceOrCreate(chatId);

        if (user.getRole().equals(UserRole.ADMIN)) {
            editSeance.setActive(true);
            editSeance.setUsers(userService.getAllUsers());
            editSeance.setEditCardType(EditCardType.EDIT_USER_DATA);
            editSeance.setEditCardStage(EditCardStage.SHOW_EDIT_USER_START_MESSAGE);
            editSeance.setCurrentIndex(0);
            return new EditEngineCommand().execute(message, isCallback, callbackId);
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }
}
