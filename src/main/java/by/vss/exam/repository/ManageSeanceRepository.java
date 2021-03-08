package by.vss.exam.repository;

import by.vss.exam.bean.manage.ManageSeance;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManageSeanceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManageSeanceRepository.class);
    private static final ManageSeanceRepository INSTANCE = new ManageSeanceRepository();
    private HashMap<Long, ManageSeance> manageSeances;

    private ManageSeanceRepository() {
        manageSeances = new HashMap<>();
    }

    public static ManageSeanceRepository getInstance() {
        return INSTANCE;
    }

    public void addManageSeance(Long id, ManageSeance manageSeance) {
        LOGGER.info("ManageSeance with id:{} was added to repository", id);
        manageSeances.put(id, manageSeance);
    }

    public boolean containsManageSeance(Long id) {
        return manageSeances.containsKey(id);
    }

    public ManageSeance getManageSeanceById(Long id) throws ExamRepositoryException {
        if (manageSeances.containsKey(id)) {
            LOGGER.info("ManageSeance with id: {} contains and got from repository", id);
            return manageSeances.get(id);
        }
        LOGGER.warn("Repository doesn't have any manageSeances with such Id");
        throw new ExamRepositoryException("Repository doesn't have any manageSeances with such Id");
    }

    public void removeManageSeanceById(Long id) throws ExamRepositoryException {
        if (manageSeances.containsKey(id)) {
            manageSeances.entrySet().removeIf(entry -> entry.getKey().equals(id));
            LOGGER.info("ManageSeance with id: {} was removed from repository", id);
        } else {
            LOGGER.warn("Repository doesn't have any manageSeances with such Id to remove");
            throw new ExamRepositoryException("Repository doesn't have any manageSeances with such Id to remove");
        }
    }

    public List<ManageSeance> getAll() throws ExamRepositoryException {
        List<ManageSeance> result = new ArrayList<>(manageSeances.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any manageSeances");
    }
}
