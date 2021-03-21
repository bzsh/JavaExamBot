package by.vss.exam.bean.manage.createCard;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.task.Task;

import java.util.Objects;

public class CreateCardSeance {
    private boolean isActive = false;
    private boolean onReceived = false;
    private String userString;
    private CreateCardType createCardType;
    private CreateCardStage createCardStage;
    private Card englishCard;
    private Card javaCard;
    private Task test;
    private Long id;

    public CreateCardSeance(Long id) {
        this.id = id;
    }

    public CreateCardSeance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnReceived() {
        return onReceived;
    }

    public void setOnReceived(boolean onReceived) {
        this.onReceived = onReceived;
    }

    public String getUserString() {
        return userString;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public Card getEnglishCard() {
        return englishCard;
    }

    public void setEnglishCard(Card englishCard) {
        this.englishCard = englishCard;
    }

    public Card getJavaCard() {
        return javaCard;
    }

    public void setJavaCard(Card javaCard) {
        this.javaCard = javaCard;
    }

    public Task getTest() {
        return test;
    }

    public void setTest(Task test) {
        this.test = test;
    }

    public CreateCardType getCreateCardType() { return createCardType; }

    public void setCreateCardType(CreateCardType createCardType) {
        this.createCardType = createCardType;
    }

    public CreateCardStage getCreateCardStage() {
        return createCardStage;
    }

    public void setCreateCardStage(CreateCardStage createCardStage) { this.createCardStage = createCardStage; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCardSeance that = (CreateCardSeance) o;
        return isActive == that.isActive && onReceived == that.onReceived && Objects.equals(userString, that.userString) && createCardType == that.createCardType && createCardStage == that.createCardStage && Objects.equals(englishCard, that.englishCard) && Objects.equals(javaCard, that.javaCard) && Objects.equals(test, that.test) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, onReceived, userString, createCardType, createCardStage, englishCard, javaCard, test, id);
    }
}
