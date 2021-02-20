package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class OptionalCardsCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
//        Long id = message.getChatId();
//        SendMessage sendMessage;
//        String menu =
//                "*Тренировка по выбранным вами карточкам*\n" +
//                        "*Java* - `карточки Java`\n" +
//                        "*English* - `английские слова`\n" +
//                        "*Back* - `Вернуться в главное меню`";
//        List<String> buttonNames = Arrays.asList("Java", "English", "Optional", "Back");
//        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
//        sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(id, markup, menu);
//        return new CommandResult(sendMessage);
        return new OnDevCommand().execute(message, isCallback, callbackId);
    }
}
