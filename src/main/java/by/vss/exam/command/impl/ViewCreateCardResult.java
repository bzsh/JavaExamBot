package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ViewCreateCardResult implements Command {
    ManageSeanceService seanceService;
    ManageSeance manageSeance;
    Integer messageId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);

        if (!manageSeance.getManageStage().equals(ManageStage.SHOW_QUESTION)) {
            manageSeance.setManageStage(ManageStage.SHOW_QUESTION);
        } else if (manageSeance.getManageStage().equals(ManageStage.SHOW_QUESTION)) {
            manageSeance.setManageStage(ManageStage.SHOW_ANSWER);
        }
        return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
    }
}
