package by.vss.exam.command.impl;

import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;


public class NextCardCommand implements Command {
    CardStudyService studyService;
    CardStudy study;
//    Card currentCard;   // random rotated
//    CardType type;  // random rotated
    Long chatId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.isCallback = isCallback;
        studyService = new CardStudyService();
        study = studyService.getStudy(chatId);
//        currentCard = study.getCurrentCard();   // random rotated
//        type = currentCard.getCardType();   // random rotated
//        study.setRotated(getRandomBooleanByCardType());   // random rotated
        if (study.hasNext()) {
            study.getNext();
            return new CardEngineCommand().execute(message, isCallback, callbackId);
        } else {
            AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
            callbackQuery.setShowAlert(false);
            callbackQuery.setCallbackQueryId(callbackId);
            callbackQuery.setText("Это последняя карточка, листай назад !");
            return new CommandResult(callbackQuery);
        }
    }

//    private boolean getRandomBooleanByCardType() { // random rotated
//        Random random = new Random();
//        boolean result = false;
//        if (type.equals(CardType.ENGLISH)) {
//            result = random.nextBoolean();
//        }
//        return result;
//    }
}