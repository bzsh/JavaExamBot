package by.vss.exam.command.impl;
import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.bean.manage.editCard.EditCardStage;
import by.vss.exam.command.Command;
import by.vss.exam.command.CommandResult;
import by.vss.exam.service.EditCardSeanceService;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RotateAndViewEditCardCommand implements Command {
    EditCardSeanceService seanceService;
    EditCardSeance editCardSeance;
    Long chatId;

    @Override
    public CommandResult execute(Message message, boolean isCallback, String callbackId) {
        chatId = message.getChatId();
        seanceService = new EditCardSeanceService();
        editCardSeance = seanceService.getEditCardSeance(chatId);

        if (!editCardSeance.getEditCardStage().equals(EditCardStage.SHOW_QUESTION)) {
            editCardSeance.setEditCardStage(EditCardStage.SHOW_QUESTION);
        } else if (editCardSeance.getEditCardStage().equals(EditCardStage.SHOW_QUESTION)) {
            editCardSeance.setEditCardStage(EditCardStage.SHOW_ANSWER);
        }
        return new EditEngineCommand().execute(message, isCallback, callbackId);
    }
}
