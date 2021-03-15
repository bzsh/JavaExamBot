package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SaveCardCommand implements Command {
    ManageSeanceService seanceService;
    ManageSeance manageSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);
        manageSeance.setManageStage(ManageStage.SAVED_CARD);
        return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
    }
}
