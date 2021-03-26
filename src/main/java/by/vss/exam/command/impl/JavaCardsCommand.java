package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.card.CardType;
import by.vss.exam.bean.user.statistic.Statistic;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.CardStudyService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class JavaCardsCommand implements Command {
    User user;
    Long chatId;
    CardStudy study;
    CardService cardService;
    UserService userService;
    Statistic userStatistics;
    CardStudyService studyService;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        cardService = new CardService();
        studyService = new CardStudyService();
        study = studyService.getStudyOrCreate(chatId);
        study.setRotated(false);
        studyService.doStudyAsNew(study, CardType.JAVA);

        List<Card> cards;
        if (study.isOptional()) {
            userService = new UserService();
            user = userService.getUser(chatId);
            userStatistics = user.getUserStatistic();
            cards = cardService.getUserCards(userStatistics.getOnLearnJavaCard(), CardType.JAVA);
        } else {
            cards = cardService.getShuffledApprovedCardsList(CardType.JAVA);
        }
        if (cards.isEmpty() && study.isOptional()) {
            String response = "У вас нет сохраненных Java карточек!";
            List<String> buttonNames = Arrays.asList("Назад");
            List<String> buttonQueries = Arrays.asList("Optionаl");
            InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            return new CommandResult(SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, response));
        }
        study.setNewCards(cards);
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }
}
