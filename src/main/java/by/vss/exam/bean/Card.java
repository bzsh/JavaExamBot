package by.vss.exam.bean;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.HashMap;
import java.util.Objects;
@JsonAutoDetect
public class Card {
    private Long cardId;
    private String sideA;
    private String sideB;
    private HashMap<Long, String> comments;

    public Card(Long cardId, String question, String answer, HashMap<Long, String> comments) {
        this.cardId = cardId;
        this.sideA = question;
        this.sideB = answer;
        this.comments = comments;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
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
        return Objects.equals(cardId, card.cardId) && Objects.equals(sideA, card.sideA) && Objects.equals(sideB, card.sideB) && Objects.equals(comments, card.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, sideA, sideB, comments);
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", question='" + sideA + '\'' +
                ", answer='" + sideB + '\'' +
                '}';
    }
}
