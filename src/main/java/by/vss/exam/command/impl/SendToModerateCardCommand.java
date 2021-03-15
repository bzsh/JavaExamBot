package by.vss.exam.command.impl;

import by.vss.exam.bean.User;
import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SendToModerateCardCommand implements Command {
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    ManageSeanceService seanceService;
    UserService userService;
    ManageSeance manageSeance;
    Long adminChatId;
    Long chatId;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        adminChatId = Long.parseLong(bundle.getString(ConstantHolder.ADMIN_ID));
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);
        seanceService.doManageSeanceAsNew(manageSeance);
        userService = new UserService();
        user = userService.getUser(chatId);
        List<SendMessage> sendMessages = new ArrayList<>();
        String messageToAdmin = "Пользователь " + user.getFirstName() +
                user.getLastName() + "\n" +
                ", " + user.getUserName() + " с Id: " + chatId + "\n" +
                " создал карточку, требуется модерация !";
        String menu =
                "Карточка сохранена спасибо !\n\n" +
                        "*Create* - `Создание карт`\n" +
                        "*Info* - `Информация`\n" +
                        "*Back* - `Вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Creаte", "Infо", "Contrоl", "Bаck");
        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, menu);
        SendMessage adminSendMessage = SendMessageCreator.createSendMessage(adminChatId, messageToAdmin);
        sendMessages.add(adminSendMessage);
        sendMessages.add(sendMessage);
        return new CommandResult(sendMessages);
    }
}
