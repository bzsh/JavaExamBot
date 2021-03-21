package by.vss.exam.command.impl;

import by.vss.exam.bean.card.Card;
import by.vss.exam.bean.card.CardType;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.bean.manage.editCard.EditCardType;
import by.vss.exam.bean.user.User;
import by.vss.exam.bean.user.UserRole;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.EditCardSeanceService;
import by.vss.exam.service.UserService;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public class EditJavaCardCommand implements Command {
    EditCardSeanceService editSeanceService;
    EditCardSeance editSeance;
    CardService cardService;
    UserService userService;
    User user;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        cardService = new CardService();
        editSeanceService = new EditCardSeanceService();
        editSeance = editSeanceService.getEditCardSeanceOrCreate(chatId);
        userService = new UserService();
        user = userService.getUser(chatId);

        if (isCallback) {
            editSeance.setCards(getCardsByUserRole());
            editSeance.setEditCardType(EditCardType.EDIT_JAVA_CARD);
            editSeance.setEditCardStage(EditCardStage.SHOW_EDIT_CARD_START_MESSAGE);
            editSeance.setCurrentIndex(0);
            return new EditEngineCommand().execute(message, isCallback, callbackId);
        } else {
            return new EmptyCommand().execute(message, isCallback, callbackId);
        }
    }

    private List<Card> getCardsByUserRole() {
        if (user.getRole().equals(UserRole.ADMIN)) {
            return cardService.getAllCards(CardType.JAVA);
        } else {
            return getCardsByUserID(chatId);
        }
    }

    private List<Card> getCardsByUserID(Long id) {
        List<Card> allCards = cardService.getAllCards(CardType.JAVA);
        List<Card> result = new ArrayList<>();
        for (Card c : allCards) {
            if (c.getCardId() > 0 && c.getCreatorId().equals(id)) {
                result.add(c);
            }
        }
        return result;
    }
}
