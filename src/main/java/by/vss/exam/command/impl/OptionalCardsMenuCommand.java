package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.User;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.CardStudyService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class OptionalCardsMenuCommand implements Command {
    CardStudyService studyService;
    Long chatId;
    CardStudy study;
    CardService cardService;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        cardService = new CardService();
        studyService = new CardStudyService();
        study = studyService.getStudyOrCreate(chatId);
        study.setOptional(true);

        SendMessage sendMessage;
        String menu =
                "*Выберите тип тренировки по вашим карточкам:*\n" +
                        "*Java* - `по выбранным вами картам Java`\n" +
                        "*English* - `по выбранным вами словам`\n" +
                        "*Back* - `Вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Java", "English", "Back");
        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(chatId, markup, menu);
        return new CommandResult(sendMessage);
    }
}
