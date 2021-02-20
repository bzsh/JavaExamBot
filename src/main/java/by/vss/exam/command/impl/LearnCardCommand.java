package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
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
    CardStudy study;
    Long chatId;
    Card card;
    User user;
    Long cardId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.isCallback = isCallback;
        userService = new UserService();
        studyService = new CardStudyService();
        this.study = studyService.getStudyOrCreate(chatId);
        this.card = study.getCurrentCard();
        this.user = userService.getUser(chatId);

        this.cardId = card.getCardId();
        Statistic userStatistic = user.getStatistics();
        boolean isOnLearn = userStatistic.getOnLearnJavaCard().contains(cardId);

        if (isOnLearn) {
            userStatistic.removeJavaCardIdToStatistic(cardId);
        } else {
            userStatistic.addJavaCardIdToStatistic(cardId);
        }
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }
}
