package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.Statistic;
import by.vss.exam.bean.User;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.CardStudyService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class EnglishCardsCommand implements Command {
    CardStudyService studyService;
    Long chatId;
    CardStudy study;
    CardService cardService;
    User user;
    UserService userService;
    Statistic userStatistics;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        cardService = new CardService();
        studyService = new CardStudyService();
        study = studyService.getStudyOrCreate(chatId);
        studyService.doStudyAsNew(study, CardType.ENGLISH);
        List<Card> cards;
        if (study.isOptional()) {
            userService = new UserService();
            user = userService.getUser(chatId);
            userStatistics = user.getStatistics();
            cards = cardService.getUserCards(userStatistics.getOnLearnEnglishCard(), CardType.ENGLISH);
        } else {
            cards = cardService.getShuffledApprovedCardsList(CardType.ENGLISH);
        }
        if (cards.isEmpty() && study.isOptional()) {
            String response = "У вас нет сохраненных карточек !";
            List<String> buttonNames = Arrays.asList("Назад");
            List<String> buttonQueries = Arrays.asList("Optionаl");
            InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            return new CommandResult(SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, response));
        }
        study.setNewCards(cards);
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }
}
