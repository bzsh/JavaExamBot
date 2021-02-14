package by.vss.exam.repository;

import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskTestRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskTestRepository.class);
    private static final TaskTestRepository INSTANCE = new TaskTestRepository();
    private HashMap<Long, TaskTest> tests;

    private TaskTestRepository() {
        tests = new HashMap<>();
    }

    public static TaskTestRepository getInstance() {
        return INSTANCE;
    }

    public void addTest(Long id, TaskTest test) {
        LOGGER.info("Test with id:{} was added to repository", id);
        tests.put(id, test);
    }

    public boolean containsTest(Long id) {
        return tests.containsKey(id);
    }

    public TaskTest getTestById(Long id) throws ExamRepositoryException {
        if (tests.containsKey(id)) {
            LOGGER.info("Test with id: {} contains and got from repository", id);
            return tests.get(id);
        }
        LOGGER.warn("Repository doesn't have any tests with such Id");
        throw new ExamRepositoryException("Repository doesn't have any tests with such Id");
    }

    public void removeTestById(Long id) throws ExamRepositoryException {
        if (tests.containsKey(id)) {
            tests.entrySet().removeIf(entry -> entry.getKey().equals(id));
            LOGGER.info("Test with id: {} was removed from repository", id);
        } else {
            LOGGER.warn("Repository doesn't have any tests with such Id to remove");
            throw new ExamRepositoryException("Repository doesn't have any tests with such Id to remove");
        }
    }

    public List<TaskTest> getAll() throws ExamRepositoryException {
        List<TaskTest> result = new ArrayList<>(tests.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any tests");
    }
}

