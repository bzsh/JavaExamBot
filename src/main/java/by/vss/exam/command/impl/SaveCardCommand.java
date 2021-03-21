package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.createCard.CreateCardStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SaveCardCommand implements Command {
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);
        createCardSeance.setCreateCardStage(CreateCardStage.SAVED_CARD);
        return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
    }
}
