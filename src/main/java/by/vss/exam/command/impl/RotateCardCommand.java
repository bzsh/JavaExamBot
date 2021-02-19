package by.vss.exam.command.impl;

import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import by.vss.exam.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RotateCardCommand implements Command {
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
        study.setRotated(!study.isRotated());
        return new CardCommand().execute(message, isCallback, callbackId);
    }
}
