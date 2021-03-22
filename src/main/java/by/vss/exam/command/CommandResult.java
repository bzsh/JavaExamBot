package by.vss.exam.command;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.util.List;

public class CommandResult {
    private SendMessage sendMessage;
    private EditMessageText editMessage;
    private DeleteMessage deleteMessage;
    private AnswerCallbackQuery answerCallbackQuery;
    private List<SendMessage> sendMessages;

    public CommandResult(DeleteMessage deleteMessage, SendMessage sendMessage) {
        this.sendMessage = sendMessage;
        this.deleteMessage = deleteMessage;
    }

    public CommandResult(SendMessage sendMessage) {
        this.sendMessage = sendMessage;
    }

    public CommandResult(EditMessageText editMessage) {
        this.editMessage = editMessage;
    }

    public CommandResult(DeleteMessage deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public CommandResult(SendMessage sendMessage, AnswerCallbackQuery answerCallbackQuery) {
        this.sendMessage = sendMessage;
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public CommandResult(EditMessageText editMessage, AnswerCallbackQuery answerCallbackQuery) {
        this.editMessage = editMessage;
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public CommandResult(AnswerCallbackQuery answerCallbackQuery) {
        this.answerCallbackQuery = answerCallbackQuery;
    }

    public CommandResult(List<SendMessage> sendMessages) {
        this.sendMessages = sendMessages;
    }

    public boolean hasSendMessages() {
        return sendMessages != null;
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

    public boolean hasDeleteAndSendMessages() {
        return deleteMessage != null && sendMessage != null;
    }

    public List<SendMessage> getSendMessages() {
        return sendMessages;
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
