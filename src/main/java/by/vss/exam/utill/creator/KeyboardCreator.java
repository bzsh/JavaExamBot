package by.vss.exam.utill.creator;

import by.vss.exam.constant.ConstantHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardCreator {
    public static Logger logger = LoggerFactory.getLogger(KeyboardCreator.class);

    public static ReplyKeyboardMarkup createReplyKeyboard(List<String> buttonNames, boolean isSelective, boolean isReSize, boolean isOnetime) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(isSelective);
        replyKeyboardMarkup.setResizeKeyboard(isReSize);
        replyKeyboardMarkup.setOneTimeKeyboard(isOnetime);

        if (buttonNames != null && buttonNames.size() > 0 & 6 >= buttonNames.size()) {
            List<KeyboardButton> buttons = createReplyKeyboardButtons(buttonNames);
            List<KeyboardRow> keyboardRows = createKeyboardRows(buttons);
            replyKeyboardMarkup.setKeyboard(keyboardRows);
        } else {
            logger.error("ButtonNames should not be null and less than zero!");
        }
        return replyKeyboardMarkup;
    }

    private static List<KeyboardButton> createReplyKeyboardButtons(List<String> buttonNames) {
        List<KeyboardButton> buttons = new ArrayList<>();
        for (String buttonName : buttonNames) {
            KeyboardButton button = new KeyboardButton(buttonName);
            buttons.add(button);
        }
        return buttons;
    }

    private static List<KeyboardRow> createKeyboardRows(List<KeyboardButton> buttons) {
        List<KeyboardRow> buttonRows = new ArrayList<>();
        if (buttons.size() % 2 == 0 && buttons.size() <= 6) {
            for (int i = 0; i < buttons.size(); i += 2) {
                KeyboardRow temp = new KeyboardRow();
                for (int j = i; j < i + 2 && j < buttons.size(); j++) {
                    temp.add(buttons.get(j));
                }
                buttonRows.add(temp);
            }
        } else {
            for (KeyboardButton button : buttons) {
                KeyboardRow temp = new KeyboardRow();
                temp.add(button);
                buttonRows.add(temp);
            }
        }
        return buttonRows;
    }

    public static InlineKeyboardMarkup createInlineKeyboard(List<String> buttonNames, List<String> buttonQueries) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        if (buttonNames != null && buttonQueries != null) {
            if (buttonNames.size() == buttonQueries.size()) {
                if (buttonNames.size() > 0 && buttonNames.size() < ConstantHolder.MAX_NUMBER_OF_ANSWERS) {
                    List<InlineKeyboardButton> buttons = createInlineKeyboardButtons(buttonNames, buttonQueries);
                    List<List<InlineKeyboardButton>> buttonRows = createInlineKeyboardButtonRows(buttons);
                    keyboardMarkup.setKeyboard(buttonRows);
                } else {
                    logger.error("Number of buttons must be more than 0 !");
                }
            } else {
                logger.error("ButtonNames size and buttonQueries size should be the same ! ");
            }
        } else {
            logger.error("ButtonNames and ButtonQueries should not be null!");
        }
        return keyboardMarkup;
    }

    private static List<List<InlineKeyboardButton>> createInlineKeyboardButtonRows(List<InlineKeyboardButton> buttons) {
        if (buttons.size() % 2 != 0 || buttons.size() % 3 == 0) {
            return getDividedButtonRows(buttons, 3);
        }
        return getDividedButtonRows(buttons, 2);
    }

    private static List<InlineKeyboardButton> createInlineKeyboardButtons(List<String> buttonNames, List<String> buttonQueries) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (int i = 0; i < buttonNames.size(); i++) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonNames.get(i));
            button.setCallbackData(buttonQueries.get(i));
            buttons.add(button);
        }
        return buttons;
    }

    private static List<List<InlineKeyboardButton>> getDividedButtonRows(List<InlineKeyboardButton> buttons, int divider) {
        List<List<InlineKeyboardButton>> buttonRows = new ArrayList<>();
        for (int i = 0; i < buttons.size(); i += divider) {
            List<InlineKeyboardButton> temp = new ArrayList<>();
            for (int j = i; j < i + divider && j < buttons.size(); j++) {
                temp.add(buttons.get(j));
            }
            buttonRows.add(temp);
        }
        return buttonRows;
    }
}
