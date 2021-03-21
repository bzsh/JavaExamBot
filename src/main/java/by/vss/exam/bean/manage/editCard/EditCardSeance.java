package by.vss.exam.bean.manage.editCard;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.user.User;

import java.util.List;
import java.util.Objects;

public class EditCardSeance {
    private boolean isActive = false;
    private boolean onReceived = false;
    private String editedString;
    private EditCardType editCardType;
    private EditCardStage editCardStage;
    private Integer currentIndex = 0;
    private List<Card> cards;
    private List<User> users;
    private Long id;

    public EditCardSeance(Long id) {
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

    public String getEditedString() {
        return editedString;
    }

    public void setEditedString(String editedString) {
        this.editedString = editedString;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCurrentUser() {
        return users.get(currentIndex);
    }

    public Card getCurrentCard() {
        return cards.get(currentIndex);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public EditCardType getEditCardType() {
        return editCardType;
    }

    public void setEditCardType(EditCardType editCardType) {
        this.editCardType = editCardType;
    }

    public EditCardStage getEditCardStage() {
        return editCardStage;
    }

    public void setEditCardStage(EditCardStage editCardStage) {
        this.editCardStage = editCardStage;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public boolean hasNextCard() {
        return currentIndex < cards.size() - 1;
    }

    public boolean hasPrevCard() {
        return currentIndex > 0;
    }

    public boolean hasNextUser() {
        return currentIndex < users.size() - 1;
    }

    public boolean hasPrevUser() {
        return currentIndex > 0;
    }

    public Card getNextCard() {
        Card result = cards.get(currentIndex);
        currentIndex++;
        return result;
    }

    public Card getPrevCard() {
        Card result = cards.get(currentIndex);
        currentIndex--;
        return result;
    }

    public User getNextUser() {
        User result = users.get(currentIndex);
        currentIndex++;
        return result;
    }

    public User getPrevUser() {
        User result = users.get(currentIndex);
        currentIndex--;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditCardSeance seance = (EditCardSeance) o;
        return isActive == seance.isActive && onReceived == seance.onReceived && Objects.equals(editedString, seance.editedString) && editCardType == seance.editCardType && editCardStage == seance.editCardStage && Objects.equals(currentIndex, seance.currentIndex) && Objects.equals(cards, seance.cards) && Objects.equals(users, seance.users) && Objects.equals(id, seance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive, onReceived, editedString, editCardType, editCardStage, currentIndex, cards, users, id);
    }
}
