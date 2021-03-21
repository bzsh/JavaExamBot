package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class MainMenuCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        Long id = message.getChatId();
        String firstname = message.getFrom().getFirstName();
        String lastname = message.getFrom().getLastName();
        String menuResponse;
        SendMessage sendMessage;
        String greetings = "`Hello` *" + firstname + "* *" + lastname + "*\n";
        String menu =
                "*Test* - `тестированиие`\n" +
                        "*Card* - `тренировка`\n" +
                        "*Manage* - `управление`\n" +
                        "*About* - `о программе`";

        if (isCallback) {
            menuResponse = menu;
        } else {
            menuResponse = greetings + menu;
        }

        List<String> buttonNames = Arrays.asList("Tеst", "Cаrd", "Mаnage", "Аbout");
        ReplyKeyboardMarkup markup = KeyboardCreator.createReplyKeyboard(buttonNames, true, true, true);
        sendMessage = SendMessageCreator.createSendMessageWithReplyKeyboard(id, markup, menuResponse);
        return new CommandResult(sendMessage);
    }
}
