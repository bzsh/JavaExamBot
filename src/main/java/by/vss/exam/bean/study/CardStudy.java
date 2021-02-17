package by.vss.exam.bean.study;

import by.vss.exam.bean.Card;

import java.util.List;

public class CardStudy {
    private Long id;
    private boolean isActive = false;
    private boolean isNew = true;
    private Integer currentCardIndex = 0;
    private List<Card> newCards;

    public boolean hasNext() {
        return currentCardIndex <= newCards.size() - 1;
    }

    public boolean hasPrev() {
        return currentCardIndex >= 0;
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
}
