package by.vss.exam.controller;

import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.command.factory.CommandFactory;
import by.vss.exam.constant.ConstantHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.ResourceBundle;

public class ExamBotController extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExamBotController.class);
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);

//    public ExamBotController() {
////        DefaultBotOptions options = getOptions();
////        options.setMaxThreads(5);
//    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }

    private void sendMsg(CommandResult commandResult) {
        SendMessage sendMessage = commandResult.getSendMessage();
        sndMsg(sendMessage);
    }

    private void editMsg(CommandResult commandResult) {
        EditMessageText editMessageText = commandResult.getEditMessage();
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void deleteMsg(CommandResult commandResult) {
        DeleteMessage deleteMessage = commandResult.getDeleteMessage();
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeCommandResult(CommandResult commandResult) {
        if (commandResult != null) {
            if (commandResult.hasAnswerCallbackQuery()) {
                sendAnswerCallback(commandResult);
            } else if (commandResult.hasEditMessage()) {
                editMsg(commandResult);
            } else if (commandResult.hasDeleteMessage()) {
                deleteMsg(commandResult);
            } else if (commandResult.hasSendMessage()) {
                sendMsg(commandResult);
            } else if (commandResult.hasSendMessages()) {
                sendMessageToSeveralUsers(commandResult);
            }
        }

    }

    public void sendAnswerCallback(CommandResult result) {
        AnswerCallbackQuery answer = result.getAnswerCallbackQuery();
        if (result.hasEditMessage()) {
            editMsg(result);
        }
        if (result.hasSendMessage()) {
            sendMsg(result);
        }
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(Update update) {
        String callbackId = "";
        Message message = update.getMessage();
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId());
        deleteMessage.setMessageId(message.getMessageId());

        LOGGER.info("\n\n <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MessageWasReceived <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n" +
                message.getText() + ", " + message.getChatId() + ", " + message.getChat().getUserName() + ", " + message.getChat().getFirstName() +
                ", " + message.getChat().getLastName() +
                ", UserId: - " + message.getFrom().getId() +
                "\n <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MessageWasReceived <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n");

        if (message.hasText()) {

            CommandFactory factory = new CommandFactory();
            Command command = factory.defineCommand(message.getText());
            CommandResult commandResult = command.execute(message, false, callbackId);
            executeCommandResult(commandResult);
        }
    }

    private void handleCallbackQuery(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();
        String callbackId = callbackQuery.getId();
        String data = callbackQuery.getData();
        Message callbackMessage = callbackQuery.getMessage();

        LOGGER.info(
                "\n\n <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CallBackQueryWasReceived <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n" +
                        data + ", " + callbackMessage.getChatId() + ", " + callbackMessage.getChat().getUserName() +
                        ", " + callbackMessage.getChat().getFirstName() + "," + callbackMessage.getChat().getLastName() +
                        ", UserId: - " + callbackQuery.getFrom().getId() + ", CallbackId: - " + callbackQuery.getId() +
                        "\n <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< CallBackQueryWasReceived <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< \n");

        CommandFactory factory = new CommandFactory();
        Command command = factory.defineCommand(data);
        CommandResult commandResult = command.execute(callbackMessage, true, callbackId);
        executeCommandResult(commandResult);
    }

    @Override
    public String getBotUsername() {
        return bundle.getString(ConstantHolder.BOT_NAME);
    }

    @Override
    public String getBotToken() {
        return bundle.getString(ConstantHolder.BOT_TOKEN);
    }

    public void sendMessageToSeveralUsers(CommandResult commandResult) {
        List<SendMessage> sendMessages = commandResult.getSendMessages();
        sendMessages.forEach(this::sndMsg);
    }

    private void sndMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
