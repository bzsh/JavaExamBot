package by.vss.exam.command.impl;

import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PrevCardCommand implements Command {
    CardStudyService studyService;
    CardStudy study;
    Long chatId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.isCallback = isCallback;
        studyService = new CardStudyService();
        study = studyService.getStudyOrCreate(chatId);
        study.setRotated(false);
        if (study.hasPrev()) {
            study.getPrev();
            return new CardEngineCommand().execute(message, isCallback, callbackId);
        } else {
            AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
            callbackQuery.setShowAlert(false);
            callbackQuery.setCallbackQueryId(callbackId);
            callbackQuery.setText("Это первая карточка, листай вперед !");
            return new CommandResult(callbackQuery);
        }
    }
}
