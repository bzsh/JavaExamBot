package by.vss.exam.bean;

import java.util.Set;

public class Statistic {
    private Set<Long> onLearnTask;
    private Set<Long> onLearnEnglishCard;
    private Set<Long> onLearnJavaCard;

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
}
