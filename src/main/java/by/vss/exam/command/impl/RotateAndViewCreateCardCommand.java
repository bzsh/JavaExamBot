package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.createCard.CreateCardStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RotateAndViewCreateCardCommand implements Command {
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    Integer messageId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);

        if (!createCardSeance.getCreateCardStage().equals(CreateCardStage.SHOW_QUESTION)) {
            createCardSeance.setCreateCardStage(CreateCardStage.SHOW_QUESTION);
        } else if (createCardSeance.getCreateCardStage().equals(CreateCardStage.SHOW_QUESTION)) {
            createCardSeance.setCreateCardStage(CreateCardStage.SHOW_ANSWER);
        }
        return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
    }
}
