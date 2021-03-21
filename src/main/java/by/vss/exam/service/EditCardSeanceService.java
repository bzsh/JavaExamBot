package by.vss.exam.service;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.EditCardSeanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class EditCardSeanceService {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final Logger LOGGER = LoggerFactory.getLogger(EditCardSeanceService.class);
    EditCardSeanceRepository editCardSeanceRepository = EditCardSeanceRepository.getInstance();

    public boolean containsEditCardSeance(Long id) {
        return editCardSeanceRepository.containsEditCardSeance(id);
    }

    public EditCardSeance getEditCardSeanceOrCreate(Long id) {
        if (containsEditCardSeance(id)) {
            LOGGER.info("EditCardSeance with id: {} was found and returned ", id);
            return getEditCardSeance(id);
        } else {
            LOGGER.info("EditCardSeance with id: {} was created and returned ", id);
            return createEditCardSeance(id);
        }
    }

    public void doEditCardSeanceAsNew(EditCardSeance editCardSeance) {
        editCardSeance.setOnReceived(false);
        editCardSeance.setActive(false);
        editCardSeance.setCards(null);
        editCardSeance.setEditedString("");
    }

    public EditCardSeance getEditCardSeance(Long id) {
        try {
            return editCardSeanceRepository.getEditCardSeanceById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new EditCardSeance(id);
    }

    private EditCardSeance createEditCardSeance(Long id) {
        EditCardSeance editCardSeance = new EditCardSeance(id);
        editCardSeanceRepository.addEditCardSeance(id, editCardSeance);
        return editCardSeance;
    }
}
