package by.vss.exam.command;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class CommandResult {
    private SendMessage sendMessage;
    private EditMessageText editMessage;
    private DeleteMessage deleteMessage;
    private AnswerCallbackQuery answerCallbackQuery;

    public CommandResult(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public CommandResult(EditMessageText editMessage) {
        this.editMessage = editMessage;
    }

    public CommandResult(DeleteMessage deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public CommandResult(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public boolean hasAnswerCallbackQuery() {
        return answerCallbackQuery != null;
    }

    public boolean hasSendMessage() {
        return sendMessage != null;
    }

    public boolean hasEditMessage() {
        return editMessage != null;
    }

    public boolean hasDeleteMessage() {
        return deleteMessage != null;
    }

    public AnswerCallbackQuery getAnswerCallbackQuery() {
        return answerCallbackQuery;
    }

    public SendMessage getSendMessage() {
        return sendMessage;
    }

    public EditMessageText getEditMessage() {
        return editMessage;
    }

    public DeleteMessage getDeleteMessage() {
        return deleteMessage;
    }
}
