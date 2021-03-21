package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DataReceiverCommand implements Command {
    CreateCardSeanceService createCardSeanceService;
    EditCardSeanceService editCardSeanceService;
    CreateCardSeance createCardSeance;
    EditCardSeance editCardSeance;
    boolean hasCreateCardSeance;
    boolean hasEditCardSeance;
    String userString;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();

        createCardSeanceService = new CreateCardSeanceService();
        editCardSeanceService = new EditCardSeanceService();

        hasCreateCardSeance = createCardSeanceService.containsCreateCardSeance(chatId);
        hasEditCardSeance = editCardSeanceService.containsEditCardSeance(chatId);

        if (hasCreateCardSeance) {
            createCardSeance = createCardSeanceService.getCreateCardSeance(chatId);
            if (createCardSeance.isOnReceived()) {
                userString = message.getText();
                createCardSeance.setUserString(userString);
                createCardSeance.setOnReceived(false);
                return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
            }
        }

        if (hasEditCardSeance) {
            editCardSeance = editCardSeanceService.getEditCardSeance(chatId);
            if (editCardSeance.isOnReceived()) {
                userString = message.getText();
                createCardSeance.setUserString(userString);
                createCardSeance.setOnReceived(false);
                return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
            }
        }
        return new EmptyCommand().execute(message, isCallback, callbackId);
    }
}
