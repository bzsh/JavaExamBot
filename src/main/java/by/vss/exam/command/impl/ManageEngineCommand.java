package by.vss.exam.command.impl;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.Task;
import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageType;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.ManageSeanceService;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import by.vss.exam.utill.creator.SendMessageCreator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;


public class ManageEngineCommand implements Command {
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
    Task task;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        System.out.println("in Manage Engine !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        chatId = message.getChatId();
        messageId = message.getMessageId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);
        manageType = manageSeance.getManageType();
        manageStage = manageSeance.getManageStage();
        userString = manageSeance.getUserString();
        return getResultByManageType(isCallback);
    }

    private String getResponseByManageStage() {
        switch (manageStage) {
            case SHOW_START_MESSAGE:
                return "Для того, что бы создать Java карту\n" +
                        "нужно ввести вопрос и ответ, используя \n" +
                        "соответствующие кнопки, введите нужные данные.\n" +
                        "для отмены нажмите выход.";
            case RECEIVED_QUESTION:
                return "-Отлично,\n" +
                        "вопрос к Java карте принят,\n" +
                        "теперь нужно ввести ответ !";
            case RECEIVED_ANSWER:

                break;
            case READY_TO_SAVE:
        }
        return null;
    }

    private CommandResult getResultByManageType(boolean isCallback) {
        CommandResult result = null;
        switch (manageType) {
            case CREATE_JAVA_CARD:
                javaCard = cardService.createCard(CardType.JAVA);
                doLogicByManageStage();
                result = getResultOfCallback(isCallback, response);

            case CREATE_ENGLISH_CARD:

                break;
            case CREATE_TEST:

                break;
            case EDIT_ENGLISH_CARD:

                break;
            case EDIT_JAVA_CARD:

                break;
            case EDIT_TEST:
                break;
        }
        return result;
    }

    private void doLogicByManageStage(){
        switch(manageStage){
            case SHOW_START_MESSAGE:
                response = getResponseByManageStage();
                List<String> buttonNames = Arrays.asList("Ввести вопрос", "Выход");
                List<String> buttonQueries = Arrays.asList("enter_question", "quit_create");
                markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
                break;
            case RECEIVED_QUESTION:
                if(userString != null && !userString.isEmpty())
                break;
            case RECEIVED_ANSWER:
                break;
            case READY_TO_SAVE:
                break;
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
//
//    private CommandResult getCommandResult(boolean isCallback){
//        return getResultOfCallback(isCallback, response);
//    }

    List<String> buttonNames = Arrays.asList("Ввести вопрос", "Выход");
//    List<String> buttonQueries = Arrays.asList("enter_question", "enter_answer", "quit_create");
//    markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
}

