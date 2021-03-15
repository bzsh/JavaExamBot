package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageType;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.FrameCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class CreateCardEngineCommand implements Command {
    public static final String CONTROL_MENU_INFO = "Используйте кнопку\n" +
            "*- просмотр*\n" +
            "Для просмотра каждой стороны \n" +
            "вашей карточки. Для редактирования используйте кнопки\n" +
            "*- изменить вопрос*\n" +
            "или\n" +
            "*- изменить ответ* \n" +
            "Для сохранения нажмите кнопку\n" +
            "*- сохранить*\n" +
            "После модерации ваша карта появится\n" +
            "среди остальных карточек и будет \n" +
            "доступна для обучения. Что бы выйти\n" +
            "в меню управления нажминте\n" +
            "*- выход*";
    public static final String CREATING_GREETINGS_TEXT =
            "\n" +
                    "Для того, что бы создать карту\n" +
                    "нужно ввести вопрос и ответ, используя \n" +
                    "соответствующие кнопки, введите нужные данные.\n" +
                    "для отмены нажмите выход.";
    public static final String SUCCESSFUL_ENTERING_TEXT =
            "-Отлично," +
                    "вопрос к карте принят,\n" +
                    "теперь введите ответ.";
    public static final String ENTERING_COMPLETE_TEXT =
            "*Вопрос и ответ для карты приняты!*\n" +
                    CONTROL_MENU_INFO;
    public static final String EDITING_QUESTION_TEXT = "*Новый вопрос принят!*\n" +
            CONTROL_MENU_INFO;
    public static final String EDITING_ANSWER_TEXT = "*Новый вответ принят!*\n" +
            CONTROL_MENU_INFO;

    public static final String SAVED_CARD_TEXT = "*Сохранить карту?*\n" +
            "нажмите:\n" +
            "*- Сохранить*, что бы сохранить и выйти\n" +
            "\n" +
            "*- Отмена*, что бы вернуться";

    ManageSeanceService seanceService;
    InlineKeyboardMarkup markup;
    ManageSeance manageSeance;
    ManageStage manageStage;
    ManageType manageType;
    String userString;
    Integer messageId;
    String response;
    Long chatId;
    CardService cardService;
    Card javaCard;
    Card englishCard;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        cardService = new CardService();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);
        javaCard = manageSeance.getJavaCard();
        englishCard = manageSeance.getEnglishCard();
        manageType = manageSeance.getManageType();
        manageStage = manageSeance.getManageStage();
        userString = manageSeance.getUserString();

        response = getResponseByManageStage();
        doLogicByManageStage();

        return getResultOfCallback(isCallback, response);
    }

    private String getResponseByManageStage() {
        switch (manageStage) {
            case SHOW_START_MESSAGE:
                return manageType.getStringOf() +
                        CREATING_GREETINGS_TEXT;
            case RECEIVED_QUESTION:
                return SUCCESSFUL_ENTERING_TEXT;
            case RECEIVED_ANSWER:
                return ENTERING_COMPLETE_TEXT;
            case RECEIVED_EDITED_QUESTION:
                return EDITING_QUESTION_TEXT;
            case RECEIVED_EDITED_ANSWER:
                return EDITING_ANSWER_TEXT;
            case SHOW_QUESTION:
                return getCardByManageType(manageType).getSideA();
            case SHOW_ANSWER:
                return getCardByManageType(manageType).getSideB();
            case SAVED_CARD:
                return SAVED_CARD_TEXT;
            case SHOW_CONTROL_MENU:
                return CONTROL_MENU_INFO;
        }
        return "";
    }

    private void doLogicByManageStage() {
        switch (manageStage) {
            case SHOW_START_MESSAGE:
                makeMainEnterQuestionButtons();
                break;
            case RECEIVED_QUESTION:
                getCardByManageType(manageType).setSideA("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                makeMainEnterAnswerButtons();
                break;
            case RECEIVED_ANSWER:
                getCardByManageType(manageType).setSideB("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                makeMainCreateButtons("Просмотр");
                break;
            case SHOW_QUESTION:
                makeMainCreateButtons("✔ Ответ");
                break;
            case SHOW_ANSWER:
                makeMainCreateButtons("❓ Вопрос");
                break;
            case RECEIVED_EDITED_QUESTION:
                getCardByManageType(manageType).setSideA("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                makeMainCreateButtons("❓ Вопрос");
                break;
            case RECEIVED_EDITED_ANSWER:
                getCardByManageType(manageType).setSideB("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                makeMainCreateButtons("✔ Ответ");
                break;
            case SAVED_CARD:
                makeSavedMenuButtons();
                break;
            case SHOW_CONTROL_MENU:
                makeMainCreateButtons("Просмотр");
                break;
        }
    }

    private Card getCardByManageType(ManageType manageType) {
        switch (manageType) {
            case CREATE_JAVA_CARD:
                return javaCard;
            case CREATE_ENGLISH_CARD:
                return englishCard;
            default:
                return new Card();
        }
    }

    private void makeMainCreateButtons(String button) {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList(button, "Сохранить", "Выход", "Изменить вопрос", "Изменить ответ");
        buttonQueries = Arrays.asList("view_create_card_result", "SAVE_CARD", "quit_create", "edit_question", "edit_answer");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private void makeMainEnterQuestionButtons() {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("Ввести вопрос", "Выход");
        buttonQueries = Arrays.asList("enter_question", "quit_create");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private void makeSavedMenuButtons() {
        manageSeance.setManageStage(ManageStage.SHOW_CONTROL_MENU);
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("Сохранить", "Отмена");
        buttonQueries = Arrays.asList("SEND_TO_MODERATE", "MANAGE_ENGINE_COMMAND");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private void makeMainEnterAnswerButtons() {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("Ввести ответ", "Выход");
        buttonQueries = Arrays.asList("enter_answer", "quit_create");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private CommandResult getResultOfCallback(boolean isCallback, String text) {
        if (isCallback) {
            EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, text);
            return new CommandResult(editMessageText);
        } else {
            SendMessage sendMessage = SendMessageCreator.createSendMessageWithInlineKeyboard(chatId, markup, text);
            return new CommandResult(sendMessage);
        }
    }
}

