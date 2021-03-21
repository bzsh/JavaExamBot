package by.vss.exam.utill.creator.message;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class EditMessageTextCreator {
    private EditMessageTextCreator() {
    }

    public static EditMessageText createEditMessageWithInlineMarkup(long chatId, int messageId, InlineKeyboardMarkup markup, String message) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setReplyMarkup(markup);
        editMessageText.setText(message);
        editMessageText.enableMarkdown(true);
        return editMessageText;
    }

    public static EditMessageText createEditMessage(long chatId, int messageId, String message) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(message);
        return editMessageText;
    }
}