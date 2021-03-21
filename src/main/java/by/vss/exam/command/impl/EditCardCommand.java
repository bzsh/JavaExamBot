package by.vss.exam.command.impl;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CreateCardSeanceService;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.service.UserService;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class EditCardCommand implements Command {
    InlineKeyboardMarkup markup;
    String response;
    Integer messageId;
    Long chatId;
    UserService userService;
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    EditCardSeanceService editSeanceService;
    EditCardSeance editSeance;
    User user;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        messageId = message.getMessageId();
        userService = new UserService();
        user = userService.getUser(chatId);

        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);

        if (createCardSeance.isActive()) {
            return new ViewCreateSeanceWarnMenuCommand().execute(message, isCallback, callbackId);
        } else {
            editSeanceService = new EditCardSeanceService();
            editSeance = editSeanceService.getEditCardSeanceOrCreate(chatId);
            getViewByUserRole(user.getRole());
            return getResultOfCallback(isCallback, response);
        }
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

    private void getViewByUserRole(UserRole role) {
        List<String> buttonNames;
        List<String> buttonQueries;
        switch (role) {
            case ADMIN:
                response = " *Меню администратора* \n" +
                        "*Users*/*Java*/*English* - редактирование\n" +
                        "списков по типу данных \n" +
                        "*View* - печать списков.";
                buttonNames = Arrays.asList(" Users ", " Java ", " English ", "View", "Выход");
                buttonQueries = Arrays.asList("edit_user", "edit_java", "edit_english", "print_list", "Quit_create");
                markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
                break;
            case USER:
                response = " *Меню редактирования учебных карточек* \n" +
                        "Вы сможете редактировать\n" +
                        "созданные вами карточки\n" +
                        "Выберите нужный тип карт:\n";
                buttonNames = Arrays.asList(" Java ", " English ", "Выход");
                buttonQueries = Arrays.asList("edit_java", "edit_english", "Quit_create");
                markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
                break;
        }
    }
}
