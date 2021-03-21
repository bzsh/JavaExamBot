package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.bean.manage.editCard.EditCardType;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class NextCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editCardSeance;
    EditCardStage editCardStage;
    EditCardType editCardType;
    boolean isCallback;
    String callbackId;
    Message message;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        this.callbackId = callbackId;
        this.isCallback = isCallback;
        this.message = message;
        editSeanceService = new EditCardSeanceService();
        editCardSeance = editSeanceService.getEditCardSeance(chatId);
        editCardStage = editCardSeance.getEditCardStage();
        editCardType = editCardSeance.getEditCardType();

        if (editCardStage.equals(EditCardStage.SHOW_EDIT_USER_START_MESSAGE) ||
                editCardStage.equals(EditCardStage.SHOW_EDIT_CARD_START_MESSAGE)) {
            editCardSeance.setEditCardStage(EditCardStage.SHOW_USER);
            return new EditEngineCommand().execute(message, isCallback, callbackId);
        }

        if (editCardType.equals(EditCardType.EDIT_USER_DATA)) {
            return getNextUser();
        } else {
            return getNextCard();
        }
    }

    private CommandResult getCallBackCommand(String callbackId, String text) {
        AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
        callbackQuery.setShowAlert(false);
        callbackQuery.setCallbackQueryId(callbackId);
        callbackQuery.setText(text);
        return new CommandResult(callbackQuery);
    }

    private CommandResult getNextCard() {
        String text = "Последняя карточка, листай назад !";
        if (editCardSeance.hasNextCard()) {
            editCardSeance.getNextCard();
            editCardSeance.setEditCardStage(EditCardStage.SHOW_QUESTION);
            return new EditEngineCommand().execute(message, isCallback, callbackId);
        } else {
            return getCallBackCommand(callbackId, text);
        }
    }

    private CommandResult getNextUser() {
        String text = "Последняя запись, листай назад !";
        if (editCardSeance.hasNextUser()) {
            editCardSeance.getNextUser();
            editCardSeance.setEditCardStage(EditCardStage.SHOW_USER);
            return new EditEngineCommand().execute(message, isCallback, callbackId);
        } else {
            return getCallBackCommand(callbackId, text);
        }
    }
}
