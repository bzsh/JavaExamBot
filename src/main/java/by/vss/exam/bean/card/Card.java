package by.vss.exam.bean.card;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.HashMap;
import java.util.Objects;

@JsonAutoDetect
public class Card {
    private Long cardId;
    private Long creatorId;
    private Boolean isApproved;
    private CardType cardType;
    private String sideA;
    private String sideB;
    private HashMap<Long, String> comments;

    public Card() {
    }

    public Card(Long id) {
        this.cardId = id;
    }

    public Card(Long cardId, Long creatorId, Boolean isApproved, CardType cardType, String sideA, String sideB, HashMap<Long, String> comments) {
        this.cardId = cardId;
        this.creatorId = creatorId;
        this.isApproved = isApproved;
        this.cardType = cardType;
        this.sideA = sideA;
        this.sideB = sideB;
        this.comments = comments;
    }
//    public Card(Long cardId, Long creatorId, String question, String answer, HashMap<Long, String> comments) {
//        this.cardId = cardId;
//        this.sideA = question;
//        this.sideB = answer;
//        this.comments = comments;
//    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Boolean isApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getSideA() {
        return sideA;
    }

    public void setSideA(String sideA) {
        this.sideA = sideA;
    }

    public String getSideB() {
        return sideB;
    }

    public void setSideB(String sideB) {
        this.sideB = sideB;
    }

    public HashMap<Long, String> getComments() {
        return comments;
    }

    public void setComments(HashMap<Long, String> comments) {
        this.comments = comments;
    }

    public void addComment(Long userId, String comment) {
        if (comments.containsKey(userId)) {
            String oldComment = comments.get(userId);
            comments.put(userId, oldComment + "\n" + comment);
        } else {
            comments.put(userId, comment);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardId, card.cardId) && Objects.equals(creatorId, card.creatorId) && Objects.equals(isApproved, card.isApproved) && cardType == card.cardType && Objects.equals(sideA, card.sideA) && Objects.equals(sideB, card.sideB) && Objects.equals(comments, card.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, creatorId, isApproved, cardType, sideA, sideB, comments);
    }
}
