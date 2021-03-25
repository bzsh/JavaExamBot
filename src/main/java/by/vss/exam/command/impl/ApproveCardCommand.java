package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ApproveCardCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editCardSeance;
    boolean isCallback;
    String callbackId;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        this.callbackId = callbackId;
        this.isCallback = isCallback;
        editSeanceService = new EditCardSeanceService();
        editCardSeance = editSeanceService.getEditCardSeance(chatId);
        Card card = editCardSeance.getCurrentCard();
        boolean isApproved = card.isApproved();

            if (isApproved) {
                card.setApproved(false);
            } else  {
                card.setApproved(true);
            }
        return new EditEngineCommand().execute(message, isCallback, callbackId);
    }
}
