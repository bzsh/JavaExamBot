package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.message.DeleteMessageCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class QuitEngineCommand implements Command {
    InlineKeyboardMarkup markup;
    Long chatId;
    Integer messageId;
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.messageId = message.getMessageId();
        if (isCallback) {
                return viewFinishMenu();
        } else {
            return viewDeleteMessage();
        }
    }
    private CommandResult viewFinishMenu() {
        String text = "Обучение завершено !\n" +
                "Тренеруйтесь чаще, что бы\n" +
                "улучшить свои знания !";
        List<String> buttonNames = Arrays.asList("К меню");
        List<String> buttonQueries = Arrays.asList("main_menu");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        return new CommandResult(EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, text));
    }

    private CommandResult viewDeleteMessage() {
        DeleteMessage deleteMessage = DeleteMessageCreator.createDeleteMessage(chatId, messageId);
        return new CommandResult(deleteMessage);
    }
}
