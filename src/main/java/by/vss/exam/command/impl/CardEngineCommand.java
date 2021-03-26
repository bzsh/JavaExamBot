package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.card.CardType;
import by.vss.exam.bean.user.statistic.Statistic;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class CardEngineCommand implements Command {
    InlineKeyboardMarkup markup;
    CardStudyService studyService;
    UserService userService;
    Statistic userStatistic;
    Card currentCard;
    CardType cardType;
    CardStudy study;
    Long chatId;
    User user;
    Integer messageId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        userService = new UserService();
        user = userService.getUser(chatId);
        userStatistic = user.getUserStatistic();
        studyService = new CardStudyService();
        study = studyService.getStudy(chatId);
        currentCard = study.getCurrentCard();
        cardType = currentCard.getCardType();

        if (study.isNew()) {
            study.setActive(true);
            study.setNew(false);
        }

        if (study.isActive()) {
            return viewCard(isCallback);
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }

    private CommandResult viewCard(boolean isCallback) {
        String text = prepareOutputText(currentCard.getSideA(), currentCard.getSideB());
        boolean isOnLearn = getCardIsLearnedByType();
        List<String> buttonNames = createButtonNames(isOnLearn);
        List<String> buttonQueries = createButtonQueries();
        markup = getMarkup(buttonNames, buttonQueries);
        return getResultOfCallback(isCallback, text);
    }

    private String prepareOutputText(String question, String answer) {
        return study.isRotated() ? answer : question;
    }

    private List<String> createButtonNames(boolean isOnLearn) {
        List<String> buttons = new ArrayList<>();
        buttons.add("Prev");
        buttons.add("Next");
        buttons.add(isOnLearn ? "Drop from learn" : "Add to learn");
        buttons.add("Quit");
        buttons.add("Rotate");
        return buttons;
    }

    private List<String> createButtonQueries() {
        List<String> buttons = new ArrayList<>();
        buttons.add("Prev_card");
        buttons.add("Next_card");
        buttons.add("Learn_card");
        buttons.add("Quit_engine");
        buttons.add("Rotate_card");
        return buttons;
    }

    private InlineKeyboardMarkup getMarkup(List<String> buttonNames, List<String> buttonQueries) {
        return KeyboardCreator.createInlineCardKeyboard(buttonNames, buttonQueries);
    }

    private boolean getCardIsLearnedByType() {
        switch (cardType) {
            case JAVA:
                return userStatistic.getOnLearnJavaCard().contains(currentCard.getCardId());
            case ENGLISH:
                return userStatistic.getOnLearnEnglishCard().contains(currentCard.getCardId());
        }
        return false;
    }

    private CommandResult getResultOfCallback(boolean isCallback, String text) {
        if (isCallback) {
            EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, text);
            return new CommandResult(editMessageText);
        } else {
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, text);
            return new CommandResult(sendMessage);
        }
    }
}
