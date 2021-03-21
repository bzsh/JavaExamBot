package by.vss.exam.service;

import by.vss.exam.bean.task.Task;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.TaskTestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TaskTestService {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final Logger LOGGER = LoggerFactory.getLogger(TaskTestService.class);
    public static final int NUMBER_OF_TASKS_IN_TEST = Integer.parseInt(bundle.getString(ConstantHolder.NUM_OF_TASKS));
    TaskTestRepository testRepository = TaskTestRepository.getInstance();
    TaskService taskService = new TaskService();

    public boolean containsTest(Long id) {
        return testRepository.containsTest(id);
    }

    public TaskTest getTestOrCreate(Long id) {
        if (containsTest(id)) {
            LOGGER.info("Test with id: {} was found and returned ", id);
            return getTest(id);
        } else {
            LOGGER.info("Test with id: {} was created and returned ", id);
            return createTest(id);
        }
    }

    public void doTestAsNew(TaskTest oldTest) {
        List<Task> tasks = taskService.getShuffledTasksList(NUMBER_OF_TASKS_IN_TEST);
        oldTest.setNewTasks(tasks);
        oldTest.setCurrentTaskIndex(0);
        oldTest.resetUserAnswers();
        oldTest.setNew(true);
        oldTest.setActive(false);
    }

    private TaskTest getTest(Long id) {
        try {
            return testRepository.getTestById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new TaskTest(id);
    }

    private TaskTest createTest(Long id) {
        TaskTest test = new TaskTest(id);
        List<Task> tasks = taskService.getShuffledTasksList(NUMBER_OF_TASKS_IN_TEST);
        List<String> answers = new ArrayList<>(NUMBER_OF_TASKS_IN_TEST);
        test.setUserAnswers(answers);
        test.setNewTasks(tasks);
        testRepository.addTest(id, test);
        return test;
    }
}
