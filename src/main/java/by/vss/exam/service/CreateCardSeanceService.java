package by.vss.exam.service;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.CreateCardSeanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class CreateCardSeanceService {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final Logger LOGGER = LoggerFactory.getLogger(CreateCardSeanceService.class);
    CreateCardSeanceRepository createCardSeanceRepository = CreateCardSeanceRepository.getInstance();

    public boolean containsCreateCardSeance(Long id) {
        return createCardSeanceRepository.containsCreateCardSeance(id);
    }

    public CreateCardSeance getCreateCardSeanceOrCreate(Long id) {
        if (containsCreateCardSeance(id)) {
            LOGGER.info("CreateCardSeance with id: {} was found and returned ", id);
            return getCreateCardSeance(id);
        } else {
            LOGGER.info("CreateCardSeance with id: {} was created and returned ", id);
            return createCreateCardSeance(id);
        }
    }

    public void doCreateCardSeanceAsNew(CreateCardSeance createCardSeance) {
        createCardSeance.setOnReceived(false);
        createCardSeance.setActive(false);
        createCardSeance.setJavaCard(null);
        createCardSeance.setEnglishCard(null);
        createCardSeance.setUserString("");
    }

    public CreateCardSeance getCreateCardSeance(Long id) {
        try {
            return createCardSeanceRepository.getCreateCardSeanceById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new CreateCardSeance(id);
    }

    private CreateCardSeance createCreateCardSeance(Long id) {
        CreateCardSeance createCardSeance = new CreateCardSeance(id);
        createCardSeanceRepository.addCreateCardSeance(id, createCardSeance);
        return createCardSeance;
    }
}
