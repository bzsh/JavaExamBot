package by.vss.exam.service;

import by.vss.exam.bean.task.Task;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.TaskRepository;
import by.vss.exam.utill.shuffle.Shuffler;

import java.util.*;

public class TaskService {
    TaskRepository taskRepository = TaskRepository.getInstance();

    public TaskService() {
        //taskRepository.refreshRepositoryFromFile();
    }

    public List<Task> getAllTasks() {
        List<Task> result = new ArrayList<>();
        try {
            result = taskRepository.getAllTasks();
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Task> getShuffledTasksList(int numberOfTasks) {
        int size = taskRepository.getRepositorySize();

        if (numberOfTasks > size) {
            numberOfTasks = size;
        }

        List<Long> list = Shuffler.getShuffledList(numberOfTasks, size);
        List<Task> tasks = new ArrayList<>(numberOfTasks);

        try {
            for (Long l : list) {
                tasks.add(taskRepository.getTaskById(l));
            }
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
            return tasks;
        }
        return tasks;
    }

}
