package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.bean.manage.editCard.EditCardType;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.frame.FrameCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class EditEngineCommand implements Command {
    public static final String ADMIN_EDIT_CARD_GREETINGS_TEXT = "\n" +
            "   *Меню редактирования карточек*\n" +
            "Используй:\n" +
            "- *⏪ & ⏩* что бы сменить карточку\n" +
            "- *Show sides* что бы просматривать стороны карточки\n" +
            "- *Approve*/*Disapprove* что бы допустить или \n" +
            "не допускать карточку к общему просмотру\n" +
            "- *Change question* что бы изменить вопрос карточки\n" +
            "- *Change answer* что бы изменить ответ карточки\n" +
            "- *By ID card* что бы нати карточку по ID\n" +
            "- *Exit* что бы выйти в меню.";
    public static final String USER_EDIT_CARD_GREETINGS_TEXT =
            "   *Меню редактирования карточек*\n" +
                    "Используй:\n" +
                    "- *Назад & Вперед* что бы менять карточки\n" +
                    "- *Просмотр* что бы просматривать стороны карточки\n" +
                    "- *Сохранить* что бы сохранить и отправить на проверку\n" +
                    "- *Изм. вопрос* что бы изменить вопрос карточки\n" +
                    "- *Изм. ответ* что бы изменить ответ карточки\n" +
                    "- *Выход* что бы выйти в меню.)";
    public static final String EDIT_USER_GREETINGS_TEXT = "\n" +
            "   *Меню редактирования данных пользователей.*\n" +
            "Используй:\n" +
            "- *⏪ & ⏩* что бы сменить учетную запись юзера\n" +
            "- *Change role* что бы сменить роль пользователя\n" +
            "- *Find by ID* что бы найти пользователя по его ID\n" +
            "- *Exit* что бы выйти в меню.";
    public static final String EDITING_QUESTION_TEXT = "*Новый вопрос принят!*\n";
    public static final String EDITING_ANSWER_TEXT = "*Новый вответ принят!*\n";
    public static final String SAVED_CARD_TEXT = "*Сохранить карту?*\n" +
            "нажмите:\n" +
            "*- Сохранить*, что бы сохранить и выйти\n" +
            "\n" +
            "*- Отмена*, что бы вернуться";

    EditCardSeanceService editSeanceService;
    InlineKeyboardMarkup markup;
    EditCardSeance editCardSeance;
    EditCardStage editCardStage;
    EditCardType editCardType;
    UserService userService;
    Integer messageId;
    Long chatId;
    User user;
    String userString;
    UserRole userRole;
    String response;
    Card currentCard;
    User currentUser;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        userService = new UserService();
        user = userService.getUser(chatId);
        editSeanceService = new EditCardSeanceService();
        editCardSeance = editSeanceService.getEditCardSeance(chatId);
        editCardStage = editCardSeance.getEditCardStage();
        editCardType = editCardSeance.getEditCardType();
        userRole = user.getRole();

        makeButtonsByEditSeanceType();

        response = getResponseByEditCardStage();

        doLogicByEditCardStage();

        return getResultOfCallback(isCallback, response);
    }

    private String getStartMessageByUserRole() {
        switch (userRole) {
            case USER:
                return USER_EDIT_CARD_GREETINGS_TEXT;
            case ADMIN:
                return ADMIN_EDIT_CARD_GREETINGS_TEXT;
        }
        return USER_EDIT_CARD_GREETINGS_TEXT;
    }

    private void makeButtonsByEditSeanceType() {
        if (editCardType.equals(EditCardType.EDIT_USER_DATA)) {
            currentUser = editCardSeance.getCurrentUser();
            makeAdminEditUserButtons();
        } else {
            currentCard = editCardSeance.getCurrentCard();
            makeEditCardButtonsByUserRole();
        }
    }

    private String getResponseByEditCardStage() {
        switch (editCardStage) {
            case SHOW_EDIT_CARD_START_MESSAGE:
                return getStartMessageByUserRole();
            case SHOW_EDIT_USER_START_MESSAGE:
                return EDIT_USER_GREETINGS_TEXT;
            case RECEIVED_EDITED_QUESTION:
                return EDITING_QUESTION_TEXT;
            case RECEIVED_EDITED_ANSWER:
                return EDITING_ANSWER_TEXT;
            case SHOW_QUESTION:
                return getQuestionByUserRole();
            case SHOW_ANSWER:
                return getAnswerByUserRole();
            case SHOW_USER:
                return showCurrentUser();
            case SAVED_CARD:
                return SAVED_CARD_TEXT;
        }
        return "";
    }

    private String getCardInfo() {
        String isApproved = currentCard.isApproved() ? "*Approved*" : "*Disapproved*";
        return "*CardId*: " + currentCard.getCardId() + "/" +
                "*Creator*: " + currentCard.getCreatorId() + "/" +
                isApproved + "\n";
    }

    private String getQuestionByUserRole() {
        if (userRole.equals(UserRole.ADMIN)) {
            return getCardInfo() + currentCard.getSideA();
        } else {
            return currentCard.getSideA();
        }
    }

    private String getAnswerByUserRole() {
        if (userRole.equals(UserRole.ADMIN)) {
            return getCardInfo() + currentCard.getSideB();
        } else {
            return currentCard.getSideB();
        }
    }

    private void doLogicByEditCardStage() {
        switch (editCardStage) {
            case RECEIVED_EDITED_QUESTION:
                currentCard.setSideA("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                break;
            case RECEIVED_EDITED_ANSWER:
                currentCard.setSideB("`" + FrameCreator.createFrameStringMessage(userString, "║") + "`");
                break;
            case SHOW_USER:
                makeAdminEditUserButtons();
                break;
        }
    }

    private String showCurrentUser() {
        return currentUser.getUserName() +
                "/" + currentUser.getFirstName() +
                "/" + currentUser.getLastName() +
                "/" + currentUser.getRole() +
                "/" + currentUser.getUserId() + "\n";
    }

    private void makeEditCardButtonsByUserRole() {
        switch (userRole) {
            case USER:
                makeUserEditCardButtons();
                break;
            case ADMIN:
                makeAdminEditCardButtons(currentCard.isApproved() ? "Disapprove" : "Approve");
                break;
        }
    }

    private void makeUserEditCardButtons() {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("Просмотр", "Изм. вопрос", "Изм.ответ", "Назад", "Выход", "Вперед", "Сохранить");
        buttonQueries = Arrays.asList("ROTATE_AND_VIEW_EDIT_CARD", "EDIT_QUESTION", "EDIT_ANSWER", "PREV_COMMAND", "QUIT_CREATE", "NEXT_COMMAND", "SAVE_CARD");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private void makeAdminEditCardButtons(String approveButton) {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("Show sides", approveButton, "By ID card", "Exit", "Change answer", "Change question", "⏪ Prev", "Next ⏩");
        buttonQueries = Arrays.asList("ROTATE_AND_VIEW_EDIT_CARD", "APPROVE_CARD", "ENTER_CARD_ID", "QUIT_CREATE", "EDIT_ANSWER", "EDIT_QUESTION", "PREV_COMMAND", "NEXT_COMMAND");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private void makeAdminEditUserButtons() {
        List<String> buttonNames;
        List<String> buttonQueries;
        buttonNames = Arrays.asList("⏪", "Change role", "⏩", "Find by ID", "Exit");
        buttonQueries = Arrays.asList("PREV_COMMAND", "EDIT_USER_ROLE", "NEXT_COMMAND", "ENTER_CARD_ID", "QUIT_CREATE");
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
