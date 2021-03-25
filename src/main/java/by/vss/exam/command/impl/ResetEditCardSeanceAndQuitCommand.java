package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ResetEditCardSeanceAndQuitCommand implements Command {
    EditCardSeanceService seanceService;
    EditCardSeance editCardSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new EditCardSeanceService();
        editCardSeance = seanceService.getEditCardSeanceOrCreate(chatId);
        seanceService.doEditCardSeanceAsNew(editCardSeance);
        return new QuitCreateCardCommand().execute(message, isCallback, callbackId);
    }
}
