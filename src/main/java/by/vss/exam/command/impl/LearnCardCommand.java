package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.Statistic;
import by.vss.exam.bean.User;
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
    boolean isJavaType;
    boolean isEnglishType;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.isCallback = isCallback;
        this.userService = new UserService();
        this.studyService = new CardStudyService();
        this.study = studyService.getStudy(chatId);   //TOdo check for null and/or return delete this message
        this.card = study.getCurrentCard();
        this.cardType = card.getCardType();
        this.user = userService.getUser(chatId);
        this.cardId = card.getCardId();
        this.userStatistic = user.getStatistics();
        boolean isOnLearn = getCardIsLearned();
        isEnglishType = cardType.equals(CardType.ENGLISH);
        isJavaType = cardType.equals(CardType.JAVA);

        System.out.println("java: - " + isJavaType);
        System.out.println("english: - " + isEnglishType);
        System.out.println("isLearned: - " + isOnLearn);

        if (isOnLearn) {
            removeCardIdStatistic();
        } else {
            addCardIdStatistic();
        }
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }

    private boolean getCardIsLearned() {
        boolean result = false;
        if (isJavaType) {
            result = userStatistic.getOnLearnJavaCard().contains(cardId);
        } else if (isEnglishType) {
            result = userStatistic.getOnLearnEnglishCard().contains(cardId);
        }
        return result;
    }

    private void addCardIdStatistic() {
        if (isJavaType) {
            userStatistic.addJavaCardIdToStatistic(cardId);
        } else if (isEnglishType) {
            userStatistic.addEnglishCardIdToStatistic(cardId);
        }
    }

    private void removeCardIdStatistic() {
        if (isJavaType) {
            userStatistic.removeJavaCardIdToStatistic(cardId);
        } else if (isEnglishType) {
            userStatistic.removeEnglishCardIdToStatistic(cardId);
        }
    }
}
