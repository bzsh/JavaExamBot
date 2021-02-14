package by.vss.exam.bean.test;

import by.vss.exam.bean.Task;

import java.util.List;

public class TaskTest {
    private Long id;
    private boolean isActive = false;
    private boolean isNew = true;
    private Integer currentTaskIndex = 0;
    private List<Task> newTasks;
    private List<String> userAnswers;

    public TaskTest(Long id) {
        this.id = id;
    }

    public TaskTest() {
    }

    public void resetUserAnswers() {
        userAnswers.clear();
    }

    public void addUserAnswer(String answer) {
        userAnswers.add(answer);
    }

    public boolean hasNext() {
        return currentTaskIndex <= newTasks.size() - 1 || userAnswers.size() < newTasks.size();
    }

    public boolean hasPrev() {
        return currentTaskIndex >= 0;
    }

    public Task getNext() {
        Task result;
        if (currentTaskIndex > 0 && userAnswers.size() < currentTaskIndex) {
            result = newTasks.get(currentTaskIndex - 1);
        } else {
            result = newTasks.get(currentTaskIndex);
            currentTaskIndex++;
        }
//        }
        return result;
    }

    public Task getPrev() {
        Task result = newTasks.get(currentTaskIndex);
        currentTaskIndex--;
        return result;
    }

    public void setUserAnswers(List<String> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setNewTasks(List<Task> newTasks) {
        this.newTasks = newTasks;
    }

    public void setCurrentTaskIndex(Integer currentTaskIndex) {
        this.currentTaskIndex = currentTaskIndex;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isNew() {
        return isNew;
    }

    public Long getId() {
        return id;
    }

    public List<String> getUserAnswers() {
        return userAnswers;
    }

    public boolean isActive() {
        return isActive;
    }

    public Integer getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public List<Task> getNewTasks() {
        return newTasks;
    }
}
