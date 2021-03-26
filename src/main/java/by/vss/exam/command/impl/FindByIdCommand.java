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
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FindByIdCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editCardSeance;
    EditCardType editCardType;
    UserService userService;
    Integer messageId;
    Long chatId;
    User user;
    UserRole userRole;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        userService = new UserService();
        user = userService.getUser(chatId);
        editSeanceService = new EditCardSeanceService();
        editCardSeance = editSeanceService.getEditCardSeance(chatId);
        editCardType = editCardSeance.getEditCardType();
        userRole = user.getRole();

        if (userRole.equals(UserRole.ADMIN)) {
            String response = getResponseByEditCardType();
            editCardSeance.setEditCardStage(EditCardStage.FIND_BY_ID);
            editCardSeance.setOnReceived(true);

            return new CommandResult(EditMessageTextCreator.createEditMessage(chatId, messageId, response));
        }
        return new EmptyCommand().execute(message, isCallback, callbackId);
    }

    private String getResponseByEditCardType() {
        String response = "";
        switch (editCardType) {
            case EDIT_USER_DATA:
                response = " - Введите Id пользователя";
                break;
            case EDIT_JAVA_CARD:
                response = " - Введите Id Java карточки";
                break;
            case EDIT_ENGLISH_CARD:
                response = " - Введите Id English карточки";
                break;
        }
        return response;
    }
}
