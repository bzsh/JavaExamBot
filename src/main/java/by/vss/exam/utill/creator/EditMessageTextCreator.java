package by.vss.exam.utill.creator;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class EditMessageTextCreator {
    private EditMessageTextCreator() {
    }

    public static EditMessageText createEditMessage(long chatId, int messageId, InlineKeyboardMarkup markup, String message) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setReplyMarkup(markup);
        editMessageText.setText(message);
        editMessageText.enableMarkdown(true);
        return editMessageText;
    }
}