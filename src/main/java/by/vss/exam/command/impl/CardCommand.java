package by.vss.exam.command.impl;

import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardStudyService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class CardCommand implements Command {
    InlineKeyboardMarkup markup;
    CardStudyService studyService;
    CommandResult commandResult;
    CardStudy study;
    Long chatId;
    Integer messageId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.isCallback = isCallback;
        markup = new InlineKeyboardMarkup();
        studyService = new CardStudyService();
        study = studyService.getStudyOrCreate(chatId);

        if (study.isNew()) {
            study.setActive(true);
            study.setNew(false);
        }

        if (study.isActive()) {
            return viewCard();
        } else {
            return viewFinalCardsMenu();
        }


        return null;
    }
}
