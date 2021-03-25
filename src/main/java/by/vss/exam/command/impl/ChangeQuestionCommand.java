package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.createCard.CreateCardStage;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ChangeQuestionCommand implements Command {
    CreateCardSeanceService createCardSeanceService;
    CreateCardSeance createCardSeance;
    EditCardSeanceService editCardSeanceService;
    EditCardSeance editCardSeance;
    Integer messageId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        createCardSeanceService = new CreateCardSeanceService();
        createCardSeance = createCardSeanceService.getCreateCardSeanceOrCreate(chatId);
        editCardSeanceService = new EditCardSeanceService();
        editCardSeance = editCardSeanceService.getEditCardSeanceOrCreate(chatId);

        if (createCardSeance.isActive()) {
            createCardSeance.setOnReceived(true);
            createCardSeance.setCreateCardStage(CreateCardStage.RECEIVED_EDITED_QUESTION);
            String response = " - Введите новый вопрос";
            return new CommandResult(EditMessageTextCreator.createEditMessage(chatId, messageId, response));
        } else if (editCardSeance.isActive()) {
            editCardSeance.setOnReceived(true);
            editCardSeance.setEditCardStage(EditCardStage.RECEIVED_EDITED_QUESTION);
            String response = " - Введите новый вопрос";
            return new CommandResult(EditMessageTextCreator.createEditMessage(chatId, messageId, response));
        }
        return new EmptyCommand().execute(message, isCallback, callbackId);
    }
}
