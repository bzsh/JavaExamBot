package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageType;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class EditEnglishCardCommand implements Command {
    ManageSeanceService seanceService;
    ManageSeance manageSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);

        manageSeance.setManageType(ManageType.EDIT_ENGLISH_CARD);
        manageSeance.setActive(true);

        return new ManageEngineCommand().execute(message, isCallback, callbackId);
    }
}
