package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.card.CardType;
import by.vss.exam.bean.user.User;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class PrintEnglishCommand implements Command {
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
        response = getResponseStringFromEnglishCards();

        List<String> buttonNames = Arrays.asList("Назад");
        List<String> buttonQueries = Arrays.asList("print_list");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, response);
        return new CommandResult(editMessageText);
    }

    private String getResponseStringFromEnglishCards() {
        StringBuilder builder = new StringBuilder();
        List<Card> cards = cardService.getAllCards(CardType.ENGLISH);
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
