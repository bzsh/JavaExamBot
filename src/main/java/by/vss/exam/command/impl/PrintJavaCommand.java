package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.card.CardType;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class PrintJavaCommand implements Command {
    InlineKeyboardMarkup markup;
    Integer messageId;
    String response;
    CardService cardService;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        cardService = new CardService();
        response = getResponseStringFromJavaCards();

        List<String> buttonNames = Arrays.asList("Сохранить", "Назад");
        List<String> buttonQueries = Arrays.asList("SAVE_JAVA_TO_FILE", "print_list");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, response);
        return new CommandResult(editMessageText);
    }

    private String getResponseStringFromJavaCards() {
        StringBuilder builder = new StringBuilder();
        List<Card> cards = cardService.getAllCards(CardType.JAVA);
        builder.append("*ID*").
                append('/').append("*IS APPROVED*").
                append('/').append("*CREATOR ID*").
                append('/').append("*TYPE*").append('\n').append('\n');
        for (Card c : cards) {
            builder.append(c.getCardId()).
                    append('/').append(c.isApproved()).
                    append('/').append(c.getCreatorId()).
                    append('/').append(c.getCardType()).append('\n');
        }
        return builder.toString();
    }
}
