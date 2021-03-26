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
import org.telegram.telegrambots.meta.api.objects.Message;

public class LearnCardCommand implements Command {
    CardStudyService studyService;
    UserService userService;
    Statistic userStatistic;
    CardStudy study;
    CardType cardType;
    Long chatId;
    Card card;
    User user;
    Long cardId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        userService = new UserService();
        studyService = new CardStudyService();
        study = studyService.getStudy(chatId);
        card = study.getCurrentCard();
        cardType = card.getCardType();
        user = userService.getUser(chatId);
        cardId = card.getCardId();
        userStatistic = user.getUserStatistic();
        boolean isOnLearn = getCardIsLearnedByType();

        if (isOnLearn) {
            removeCardIdStatistic();
        } else {
            addCardIdStatistic();
        }
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }

    private boolean getCardIsLearnedByType() {
        switch (cardType) {
            case JAVA:
                return userStatistic.getOnLearnJavaCard().contains(card.getCardId());
            case ENGLISH:
                return userStatistic.getOnLearnEnglishCard().contains(card.getCardId());
        }
        return false;
    }

    private void addCardIdStatistic() {
        switch (cardType) {
            case JAVA:
                userStatistic.addJavaCardIdToStatistic(cardId);
                break;
            case ENGLISH:
                userStatistic.addEnglishCardIdToStatistic(cardId);
                break;
        }
    }

    private void removeCardIdStatistic() {
        switch (cardType) {
            case JAVA:
                userStatistic.removeJavaCardIdToStatistic(cardId);
                break;
            case ENGLISH:
                userStatistic.removeEnglishCardIdToStatistic(cardId);
                break;
        }
    }
}
