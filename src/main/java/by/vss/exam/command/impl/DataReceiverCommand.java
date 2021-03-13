package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DataReceiverCommand implements Command {
    ManageSeanceService manageSeanceService;
    ManageSeance manageSeance;
    boolean hasManageSeance;
    boolean onReceived;
    String userString;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        manageSeanceService = new ManageSeanceService();
        hasManageSeance = manageSeanceService.containsManageSeance(chatId);
        if (hasManageSeance) {
            manageSeance = manageSeanceService.getManageSeance(chatId);
            onReceived = manageSeance.isOnReceived();
            if (onReceived) {
                userString = message.getText();
                manageSeance.setUserString(userString);
                manageSeance.setOnReceived(false);
                return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
            } else {
                return new EmptyCommand().execute(message, isCallback, callbackId);
            }
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }
}
