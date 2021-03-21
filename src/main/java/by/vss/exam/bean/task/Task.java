package by.vss.exam.bean.task;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.*;

@JsonAutoDetect
public class Task {
    private Long taskId;
    private Long creatorId;
    private Boolean isApproved;
    private String question;
    private String rightAnswer;
    private Map<Long, String> comments;
    private List<String> listOfAnswers;

    public Task(Long taskId, Long creatorId, Boolean isApproved, String question, String rightAnswer, Map<Long, String> comments, List<String> listOfAnswers) {
        this.taskId = taskId;
        this.creatorId = creatorId;
        this.isApproved = isApproved;
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.comments = comments;
        this.listOfAnswers = listOfAnswers;
    }
//    public Task(Long taskId, Long creatorId, String question, HashMap<Long, String> comments, List<String> listOfAnswers, String rightAnswer) {
//        this.taskId = taskId;
//        this.question = question;
//        this.listOfAnswers = listOfAnswers;
//        this.rightAnswer = rightAnswer;
//        this.comments = comments;
//    }

    public Task() {
    }

    public Boolean isApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Map<Long, String> getComments() {
        return comments;
    }

    public void setComments(Map<Long, String> comments) {
        this.comments = comments;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<String> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void addComment(Long userId, String comment) {
        if (comments.containsKey(userId)) {
            String oldComment = comments.get(userId);
            comments.put(userId, oldComment + "\n   * * *   \n" + comment);
        } else {
            comments.put(userId, comment);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(creatorId, task.creatorId) && Objects.equals(isApproved, task.isApproved) && Objects.equals(question, task.question) && Objects.equals(rightAnswer, task.rightAnswer) && Objects.equals(comments, task.comments) && Objects.equals(listOfAnswers, task.listOfAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, creatorId, isApproved, question, rightAnswer, comments, listOfAnswers);
    }
}
