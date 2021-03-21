package by.vss.exam.command.impl;

import by.vss.exam.bean.task.Task;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.service.TaskTestService;
import by.vss.exam.utill.creator.message.EditMessageTextCreator;
import by.vss.exam.utill.creator.keyboard.KeyboardCreator;
import by.vss.exam.utill.creator.message.SendMessageCreator;
import by.vss.exam.command.CommandResult;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

public class TestEngineCommand implements Command {
    InlineKeyboardMarkup markup;
    TaskTestService service;
    CommandResult result;
    TaskTest test;
    Long chatId;
    Integer messageId;
    boolean isCallback;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        this.chatId = message.getChatId();
        this.messageId = message.getMessageId();
        this.service = new TaskTestService();
        this.test = service.getTestOrCreate(chatId);
        this.isCallback = isCallback;

        if (test.isNew()) {
            test.setActive(true);
            test.setNew(false);
        }

        if (test.isActive()) {
            return viewTestTask();
        } else {
            return viewFinalTestMenu();
        }
    }

    private CommandResult viewTestTask() {
        if (test.hasNext()) {
            Task nextTask = test.getNext();
            String question = nextTask.getQuestion();
            List<String> answers = nextTask.getListOfAnswers();
            markup = getMarkup(answers, answers);
            result = getResultOfCallback(isCallback, question);
        } else {
            return viewFinalTestMenu();
        }
        return result;
    }

    private InlineKeyboardMarkup getMarkup(List<String> buttonNames, List<String> buttonQueries) {
        return KeyboardCreator.createInlineKeyboard(buttonNames, buttonQueries);
    }

    private CommandResult viewFinalTestMenu() {
        double mark = calculateTestResult(test);
        String isSuccess = mark >= 70 ? "*Тест пройден!* \uD83C\uDFC6 " : "*Тест провален!* ⛔";
        String text = isSuccess + " \n" +
                "У вас " + mark + " % правильных ответов !\n" +
                "Нажмите : \n" +
                "- *Результат*, `что бы посмотреть результаты тестирования`\n" +
                "- *Завершить*, `что бы завершить тест и вернуться в главное меню`";
        List<String> buttonNames = Arrays.asList("Результат", "Завершить");
        List<String> buttonQueries = Arrays.asList("view_test_result", "end_task_test");
        markup = getMarkup(buttonNames, buttonQueries);
        result = getResultOfCallback(isCallback, text);
        test.setActive(false);
        test.setCurrentTaskIndex(0);
        return result;
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

    private Double calculateTestResult(TaskTest test) {
        List<Task> tasks = test.getNewTasks();
        List<String> answers = test.getUserAnswers();
        int totalSize = tasks.size();
        int numOfRightAnswers = 0;

        for (int i = 0; i < totalSize; i++) {
            Task task = tasks.get(i);
            if (task.getRightAnswer().equalsIgnoreCase(answers.get(i))) {
                numOfRightAnswers++;
            }
        }
        return (double) (numOfRightAnswers * 100 / totalSize);
    }
}