package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Random;

public class RotateCardCommand implements Command {
    CardStudyService studyService;
    CardStudy study;
    Long chatId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        this.isCallback = isCallback;
        studyService = new CardStudyService();
        study = studyService.getStudy(chatId);
        study.setRotated(!study.isRotated());
        return new CardEngineCommand().execute(message, isCallback, callbackId);
    }
}
