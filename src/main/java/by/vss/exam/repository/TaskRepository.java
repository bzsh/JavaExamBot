package by.vss.exam.repository;

import by.vss.exam.bean.Task;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.json.UniversalJsonReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskRepository.class);
    private static final TaskRepository INSTANCE = new TaskRepository();
    private final UniversalJsonReaderWriter<Task> taskReaderWriter = new UniversalJsonReaderWriter<>(ConstantHolder.TASK_PATH, Task.class);

    private final HashMap<Long, Task> tasks = taskReaderWriter.getAllFromFile();
    private Long lastGeneratedId;

    private TaskRepository() {
    }

    public static TaskRepository getInstance() {
        return INSTANCE;
    }

    public void addTask(Long id, Task task) {
        tasks.put(id, task);
    }

    public int getRepositorySize() {
        int repoSize = tasks.size();
        LOGGER.info("getRepositorySize method was called and returned size: {}.", repoSize);
        return repoSize;
    }

    public List<Task> getAllTasks() throws ExamRepositoryException {
        List<Task> result = new ArrayList<>(tasks.values());
        if (!result.isEmpty()) {
            return result;
        }
        LOGGER.warn("Repository doesn't have any tasks");
        throw new ExamRepositoryException("Repository doesn't have any tasks");
    }

    public Task getTaskById(Long id) throws ExamRepositoryException {
        if (tasks.containsKey(id)) {
            LOGGER.info("getTaskById method was called, task is found and returned.");
            return tasks.get(id);
        }
        LOGGER.warn("Repository doesn't have any tasks with such Id");
        throw new ExamRepositoryException("Repository doesn't have any tasks with such Id");
    }

    public void removeTaskById(Long id) throws ExamRepositoryException {
        if (tasks.containsKey(id)) {
            tasks.entrySet().removeIf(entry -> entry.getKey().equals(id));
        } else {
            throw new ExamRepositoryException("Repository doesn't have any task to remove");
        }
    }

    public void saveAllToFile()  {
        taskReaderWriter.addAllToFile(tasks);
    }
}
