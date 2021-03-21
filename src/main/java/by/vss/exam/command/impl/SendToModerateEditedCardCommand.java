package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.user.User;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ResourceBundle;

public class SendToModerateEditedCardCommand implements Command {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    EditCardSeanceService seanceService;
    EditCardSeance editCardSeance;
    UserService userService;
    Long adminChatId;
    Long chatId;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        adminChatId = Long.parseLong(bundle.getString(ConstantHolder.ADMIN_ID));
        seanceService = new EditCardSeanceService();
        editCardSeance = seanceService.getEditCardSeanceOrCreate(chatId);
        userService = new UserService();
        user = userService.getUser(chatId);

        String messageToAdmin = "Пользователь " + user.getFirstName() +
                user.getLastName() + "\n" +
                ", " + user.getUserName() + " с Id: " + chatId + "\n" +
                " отредактировал карточку!";

        SendMessage adminSendMessage = SendMessageCreator.createSendMessage(adminChatId, messageToAdmin);

        AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
        callbackQuery.setShowAlert(false);
        callbackQuery.setCallbackQueryId(callbackId);
        callbackQuery.setText("Сохранено!");

        return new CommandResult(adminSendMessage, callbackQuery);
    }
}
