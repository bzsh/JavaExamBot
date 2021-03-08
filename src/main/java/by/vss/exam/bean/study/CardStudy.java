package by.vss.exam.bean.study;

import by.vss.exam.bean.Card;

import java.util.List;
import java.util.Objects;

public class CardStudy {
    private Long id;
    private boolean isOptional = false;
    private boolean isRotated = false;
    private boolean isActive = false;
    private boolean isNew = false;
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

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardStudy study = (CardStudy) o;
        return isOptional == study.isOptional && isRotated == study.isRotated && isActive == study.isActive && isNew == study.isNew && Objects.equals(id, study.id) && Objects.equals(currentCardIndex, study.currentCardIndex) && Objects.equals(newCards, study.newCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isOptional, isRotated, isActive, isNew, currentCardIndex, newCards);
    }
}
