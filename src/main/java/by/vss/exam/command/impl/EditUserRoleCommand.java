package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.bean.manage.editCard.EditCardType;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class EditUserRoleCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editCardSeance;
    EditCardStage editCardStage;
    EditCardType editCardType;
    boolean isCallback;
    String callbackId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        this.callbackId = callbackId;
        this.isCallback = isCallback;
        editSeanceService = new EditCardSeanceService();
        editCardSeance = editSeanceService.getEditCardSeance(chatId);
        editCardStage = editCardSeance.getEditCardStage();
        editCardType = editCardSeance.getEditCardType();
        User user = editCardSeance.getCurrentUser();
        UserRole role = user.getRole();

        if (editCardType.equals(EditCardType.EDIT_USER_DATA)) {
            if (role.equals(UserRole.ADMIN)) {
                user.setRole(UserRole.USER);
            } else if (role.equals(UserRole.USER)) {
                user.setRole(UserRole.ADMIN);
            }

        }
        return new EditEngineCommand().execute(message, isCallback, callbackId);
    }
}
