package by.vss.exam.command.impl;

import by.vss.exam.bean.card.CardType;
import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.bean.manage.createCard.CreateCardStage;
import by.vss.exam.bean.manage.createCard.CreateCardType;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.CreateCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class CreateEnglishCardCommand implements Command {
    CreateCardSeanceService seanceService;
    CreateCardSeance createCardSeance;
    CardService cardService;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new CreateCardSeanceService();
        createCardSeance = seanceService.getCreateCardSeanceOrCreate(chatId);

        if (createCardSeance.isActive() && !createCardSeance.getCreateCardType().equals(CreateCardType.CREATE_ENGLISH_CARD)) {
            return new ViewCreateSeanceWarnMenuCommand().execute(message, isCallback, callbackId);
        } else if (!createCardSeance.isActive()) {
            if (createCardSeance.getEnglishCard() == null) {
                cardService = new CardService();
                createCardSeance.setEnglishCard(cardService.createCard(CardType.ENGLISH, chatId));
            }
            createCardSeance.setCreateCardType(CreateCardType.CREATE_ENGLISH_CARD);
            createCardSeance.setCreateCardStage(CreateCardStage.SHOW_START_MESSAGE);
            createCardSeance.setActive(true);
        }
        return new CreateCardEngineCommand().execute(message, isCallback, callbackId);
    }
}
