package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.createCard.CreateCardStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChangeQuestionCommand implements Command {
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
        createCardSeance.setOnReceived(true);
        createCardSeance.setCreateCardStage(CreateCardStage.RECEIVED_EDITED_QUESTION);
        String response = " - Введите новый вопрос";
        return new CommandResult(EditMessageTextCreator.createEditMessage(chatId, messageId, response));
    }
}
