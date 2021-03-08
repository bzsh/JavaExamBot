package by.vss.exam.command.impl;

import by.vss.exam.bean.Task;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.utill.creator.DeleteMessageCreator;
import by.vss.exam.utill.creator.EditMessageTextCreator;
import by.vss.exam.utill.creator.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class ViewTestResultCommand implements Command {
    InlineKeyboardMarkup markup;
    TaskTestService service;
    CommandResult result;
    Message message;
    String callbackId;
    Integer currentTaskIndex;
    TaskTest test;
    Long chatId;
    Integer messageId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.message = message;
        this.chatId = message.getChatId();
        this.messageId = message.getMessageId();
        this.service = new TaskTestService();
        this.test = service.getTestOrCreate(chatId);
        this.isCallback = isCallback;
        this.callbackId = callbackId;
        this.currentTaskIndex = test.getCurrentTaskIndex();

        if (isCallback) {
            if (test.isNew() && !test.isActive()) {
                return viewFinishMenu();
            } else {
                return reviewTestTask();
            }
        } else {
            return viewDeleteMessage();
        }
    }

    private CommandResult reviewTestTask() {
        if (test.hasNext()) {
            String taskReview = getTaskReview(test.getNext());
            List<String> buttonNames = Arrays.asList("Продолжить " + "✏");
            List<String> buttonQueries = Arrays.asList("view_test_result");
            markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
            EditMessageText editMessageText = EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, taskReview);
            result = new CommandResult(editMessageText);
        } else {
            result = new TestEngineCommand().execute(message, isCallback, callbackId);
        }
        return result;
    }

    public String getTaskReview(Task task) {
        String rightAnswer = task.getRightAnswer();
        String userAnswer = test.getUserAnswers().get(currentTaskIndex);
        String question = task.getQuestion();
        String inTextMessage = userAnswer.equalsIgnoreCase(rightAnswer) ? "*Вы ответили верно !* " + "✅" + "\n" : "*Вы ответили не верно !* " + "❌" + "\n";
        String result = question + "\n" +
                "-------------\n" +
                inTextMessage +
                "*Правильный ответ -* " +
                rightAnswer + "\n" +
                "*Ваш ответ -* " +
                userAnswer;

        return result;
    }

    private InlineKeyboardMarkup getMarkup(List<String> buttonNames, List<String> buttonQueries) {
        return KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private CommandResult viewFinishMenu() {
        String text = "Тест завершен !\n" +
                "Результат сохранен, спасибо !\n";
        List<String> buttonNames = Arrays.asList("К меню");
        List<String> buttonQueries = Arrays.asList("main_menu");
        markup = KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
        return new CommandResult(EditMessageTextCreator.createEditMessageWithInlineMarkup(chatId, messageId, markup, text));
    }

    private CommandResult viewDeleteMessage() {
        DeleteMessage deleteMessage = DeleteMessageCreator.createDeleteMessage(chatId, messageId);
        return new CommandResult(deleteMessage);
    }
}
