package by.vss.exam.utill.creator.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class SendMessageCreator {
    private SendMessageCreator() {
    }

    public static SendMessage createSendMessageWithInlineKeyboard(long chatId, InlineKeyboardMarkup markup, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText(message);
        return sendMessage;
    }

    public static SendMessage createSendMessage(long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);
        sendMessage.setText(message);
        return sendMessage;
    }

    public static SendMessage createSendMessageWithReplyKeyboard(long chatId, ReplyKeyboardMarkup markup, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(markup);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        return sendMessage;
    }
}
