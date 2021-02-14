package by.vss.exam.service;

import by.vss.exam.bean.Task;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.TaskRepository;

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

        List<Long> list = getShuffledList(numberOfTasks, size);
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

    private static ArrayList<Long> getShuffledList(int numberOfTasks, int size) {
        ArrayList<Long> numbersGenerated = new ArrayList<>();             //TODO    !!!!!!

        for (int i = 0; i < numberOfTasks; i++) {
            Random randNumber = new Random();
            long iNumber = randNumber.nextInt(size) + 1;

            if (!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
        return numbersGenerated;
    }
}
