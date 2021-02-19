package by.vss.exam.bean.study;

import by.vss.exam.bean.Card;

import java.util.List;

public class CardStudy {
    private Long id;
    private StudyType studyType;
    private boolean isRotated = false;
    private boolean isActive = false;
    private boolean isNew = true;
    private Integer currentCardIndex = 0;
    private List<Card> newCards;

    public CardStudy(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRotated() {
        return isRotated;
    }

    public void setRotated(boolean rotated) {
        isRotated = rotated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Integer getCurrentCardIndex() {
        return currentCardIndex;
    }

    public void setCurrentCardIndex(Integer currentCardIndex) {
        this.currentCardIndex = currentCardIndex;
    }

    public List<Card> getNewCards() {
        return newCards;
    }

    public void setNewCards(List<Card> newCards) {
        this.newCards = newCards;
    }

    public boolean hasNext() {
        return currentCardIndex < newCards.size() - 1;
    }

    public boolean hasPrev() {
        return currentCardIndex > 0;
    }

    public Card getNext() {
        Card result = newCards.get(currentCardIndex);
        currentCardIndex++;
        return result;
    }

    public Card getPrev() {
        Card result = newCards.get(currentCardIndex);
        currentCardIndex--;
        return result;
    }

    public Card getCurrentCard() {
        return newCards.get(currentCardIndex);
    }
}
