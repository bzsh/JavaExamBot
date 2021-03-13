package by.vss.exam.command.impl;

import by.vss.exam.bean.CardType;
import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.bean.manage.ManageStage;
import by.vss.exam.bean.manage.ManageType;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.CardService;
import by.vss.exam.service.ManageSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;


public class CreateJavaCardCommand implements Command {
    ManageSeanceService seanceService;
    ManageSeance manageSeance;
    CardService cardService;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new ManageSeanceService();
        manageSeance = seanceService.getManageSeanceOrCreate(chatId);

        if (manageSeance.isActive() && !manageSeance.getManageType().equals(ManageType.CREATE_JAVA_CARD)) {
            return new ViewManageSeanceWarningMenu().execute(message, isCallback, callbackId);
        } else if (!manageSeance.isActive()) {
            if (manageSeance.getJavaCard() == null) {
                cardService = new CardService();
                manageSeance.setJavaCard(cardService.createCard(CardType.JAVA, chatId));
            }
            manageSeance.setManageType(ManageType.CREATE_JAVA_CARD);
            manageSeance.setManageStage(ManageStage.SHOW_START_MESSAGE);
            manageSeance.setActive(true);
        }
        return new ManageEngineCommand().execute(message, isCallback, callbackId);
    }
}
