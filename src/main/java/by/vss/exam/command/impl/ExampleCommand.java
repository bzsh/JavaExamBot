package by.vss.exam.command.impl;

import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.TaskTestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class ExampleCommand implements Command {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleCommand.class);
    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        TaskTestRepository repository = TaskTestRepository.getInstance();

        List<TaskTest> taskTests = new ArrayList<>();
        try {
            taskTests = repository.getAll();
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }

        String text = "";
        for (TaskTest test : taskTests) {
            text = text + " \n\n_______ " +  " print existed TaskTests \n" +
                    " testId: - " + test.getId() +
                    ", isActive: -" + test.isActive() +
                    ", isNew: -" + test.isNew() +
                    ", currentTaskIndex: -" +
                    test.getCurrentTaskIndex() + ", newTasksSize: -" + test.getNewTasks().size() + "\n________________________________________________________________";
            LOGGER.info(text);
        }
        return  null;
    }
}
