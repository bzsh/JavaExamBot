package by.vss.exam.bean.manage;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.Task;

import java.util.Objects;

public class ManageSeance {
    private boolean isActive = false;
    private boolean onReceived = false;
    private String userString;
    private ManageType manageType;
    private ManageStage manageStage;
    private Card englishCard;
    private Card javaCard;
    private Task test;
    private Long id;

    public ManageSeance(Long id) {
        this.id = id;
    }

    public ManageSeance() {
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

    public ManageType getManageType() {
        return manageType;
    }

    public void setManageType(ManageType manageType) {
        this.manageType = manageType;
    }

    public ManageStage getManageStage() {
        return manageStage;
    }

    public void setManageStage(ManageStage manageStage) {
        this.manageStage = manageStage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManageSeance that = (ManageSeance) o;
        return isActive == that.isActive && onReceived == that.onReceived && Objects.equals(userString, that.userString) && manageType == that.manageType && manageStage == that.manageStage && Objects.equals(englishCard, that.englishCard) && Objects.equals(javaCard, that.javaCard) && Objects.equals(test, that.test) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, onReceived, userString, manageType, manageStage, englishCard, javaCard, test, id);
    }
}
