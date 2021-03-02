package by.vss.exam.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonAutoDetect
public class Statistic {
    private Set<Long> onLearnTask = new HashSet<>();
    private Set<Long> onLearnEnglishCard = new HashSet<>();
    private Set<Long> onLearnJavaCard = new HashSet<>();

    public Statistic(Set<Long> onLeanTask, Set<Long> onLearnEnglishCard, Set<Long> onLearnJavaCard) {
        this.onLearnTask = onLeanTask;
        this.onLearnEnglishCard = onLearnEnglishCard;
        this.onLearnJavaCard = onLearnJavaCard;
    }

    public Statistic() {
    }

    public Set<Long> getOnLeanTask() {
        return onLearnTask;
    }

    public void setOnLeanTask(Set<Long> onLeanTask) {
        this.onLearnTask = onLeanTask;
    }

    public Set<Long> getOnLearnEnglishCard() {
        return onLearnEnglishCard;
    }

    public void setOnLearnEnglishCard(Set<Long> onLearnEnglishCard) {
        this.onLearnEnglishCard = onLearnEnglishCard;
    }

    public Set<Long> getOnLearnJavaCard() {
        return onLearnJavaCard;
    }

    public void setOnLearnJavaCard(Set<Long> onLearnJavaCard) {
        this.onLearnJavaCard = onLearnJavaCard;
    }

    public void addJavaCardIdToStatistic(Long onLearnJavaCardId) {
        onLearnJavaCard.add(onLearnJavaCardId);
    }

    public void addEnglishCardIdToStatistic(Long onLearnEnglishCardId) {
        onLearnEnglishCard.add(onLearnEnglishCardId);
    }

    public void addTaskIdToStatistic(Long onLearnTaskId) {
        onLearnTask.add(onLearnTaskId);
    }

    public void removeJavaCardIdToStatistic(Long onLearnJavaCardId) {
        onLearnJavaCard.remove(onLearnJavaCardId);
    }

    public void removeEnglishCardIdToStatistic(Long onLearnEnglishCardId) {
        onLearnEnglishCard.remove(onLearnEnglishCardId);
    }

    public void removeTaskIdToStatistic(Long onLearnTaskId) {
        onLearnTask.remove(onLearnTaskId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(onLearnTask, statistic.onLearnTask) && Objects.equals(onLearnEnglishCard, statistic.onLearnEnglishCard) && Objects.equals(onLearnJavaCard, statistic.onLearnJavaCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onLearnTask, onLearnEnglishCard, onLearnJavaCard);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "onLearnTask=" + onLearnTask +
                ", onLearnEnglishCard=" + onLearnEnglishCard +
                ", onLearnJavaCard=" + onLearnJavaCard +
                '}';
    }
}
