package by.vss.exam.command.impl;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

public class InfoCommand implements Command {
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        String aboutMessage = "~*Info*~\n\n" +
                "При создании Java карт, \n" +
                "на стороне с ответом на вопрос\n" +
                "можно использовать спец-символы\n" +
                "для форматирования текста : \n" +
                "```  _, *, ` ```, просто окружите \n" +
                "текст одним из данных символов.\n" +
                "Пример:``` `слово`, *слово*, _слово_ ```\n" +
                "Символ `'*'` для (*жирного шрифта*).\n" +
                "- `'_'` символ нижнего подчеркивания\n" +
                "преобразует шрифт в (_курсивый_)\n" +
                "``` '`' ```данный символ для вставки\n" +
                "кода (`System.out.println();`)\n" +
                "так же данный символ можно\n" +
                "использовать для экранирования\n" +
                "одиночных символов `'*'` и `'_'` \n" +
                "одиночные, не экранированные символы\n" +
                "могут вызвать ошибку отображения,\n" +
                "лучше подготовте вопрос и текст\n" +
                "ответа, а форматирование предоставте\n" +
                "администраторам. Удачи :-)";
        List<String> button = new ArrayList<>();
        button.add("Back to Menu");
        List<String> query = new ArrayList<>();
        query.add("Mаnage");
        InlineKeyboardMarkup markup = KeyboardCreator.createInlineKeyboard(button, query);
        SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(message.getChatId(), markup, aboutMessage);
        sendMessage.enableMarkdown(true);
        sendMessage.disableWebPagePreview();
        return new CommandResult(sendMessage);
    }
}
