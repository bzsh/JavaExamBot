package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;

public class EmojiCommand implements Command {
    Long chatId;
    Integer messageId;
    String text;
    String greenButton;
    String redButton;
    String emoji;
    static boolean flag;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        text = "Какое хочешь яблочко ???";
        greenButton = "Зеленое \uD83C\uDF4F";
        redButton = "Красное \uD83C\uDF4E";
        emoji = "emoji";

        return getResultOfCallback(isCallback, text);
    }

    private CommandResult getResultOfCallback(boolean isCallback, String text) {
        if (isCallback) {
            flag = !flag;
            EditMessageText editMessageText = EditMessageTextCreator.createEditMessage(chatId, messageId, changeMarkup(flag), text);
            return new CommandResult(editMessageText);
        } else {
            flag = true;
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, changeMarkup(flag), text);
            return new CommandResult(sendMessage);
        }
    }

    private InlineKeyboardMarkup changeMarkup(boolean flag) {
        if (flag) {
            return KeyboardCreator.createInlineKeyboard(Arrays.asList(greenButton, redButton), Arrays.asList(emoji, emoji));
        } else {
            return KeyboardCreator.createInlineKeyboard(Arrays.asList(redButton, greenButton), Arrays.asList(emoji, emoji));
        }
    }
}
