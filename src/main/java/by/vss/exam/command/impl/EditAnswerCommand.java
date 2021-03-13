package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.objects.Message;

public class EditAnswerCommand implements Command {
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
        manageSeance.setOnReceived(true);
        manageSeance.setManageStage(ManageStage.RECEIVED_EDITED_ANSWER);
        String response = " - Введите новый ответ";
        return new CommandResult(EditMessageTextCreator.createEditMessage(chatId, messageId, response));
    }
}
