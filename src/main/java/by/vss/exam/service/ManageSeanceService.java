package by.vss.exam.service;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.ManageSeanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class ManageSeanceService {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final Logger LOGGER = LoggerFactory.getLogger(ManageSeanceService.class);
    ManageSeanceRepository manageSeanceRepository = ManageSeanceRepository.getInstance();

    public boolean containsManageSeance(Long id) {
        return manageSeanceRepository.containsManageSeance(id);
    }

    public ManageSeance getManageSeanceOrCreate(Long id) {
        if (containsManageSeance(id)) {
            LOGGER.info("ManageSeance with id: {} was found and returned ", id);
            return getManageSeance(id);
        } else {
            LOGGER.info("ManageSeance with id: {} was created and returned ", id);
            return createManageSeance(id);
        }
    }

    public void doManageSeanceAsNew(ManageSeance manageSeance) {
        manageSeance.setOnReceived(false);
        manageSeance.setActive(false);
        manageSeance.setEnglishCard(null);
        manageSeance.setJavaCard(null);
        manageSeance.setTest(null);
        manageSeance.setUserString("");
    }

    public ManageSeance getManageSeance(Long id) {
        try {
            return manageSeanceRepository.getManageSeanceById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new ManageSeance(id);
    }

    private ManageSeance createManageSeance(Long id) {
        ManageSeance manageSeance = new ManageSeance(id);
        manageSeanceRepository.addManageSeance(id, manageSeance);
        return manageSeance;
    }
}
