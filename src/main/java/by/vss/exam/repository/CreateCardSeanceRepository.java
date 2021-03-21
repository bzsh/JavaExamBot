package by.vss.exam.repository;

import by.vss.exam.bean.manage.createCard.CreateCardSeance;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateCardSeanceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateCardSeanceRepository.class);
    private static final CreateCardSeanceRepository INSTANCE = new CreateCardSeanceRepository();
    private HashMap<Long, CreateCardSeance> createCardSeances;

    private CreateCardSeanceRepository() {
        createCardSeances = new HashMap<>();
    }

    public static CreateCardSeanceRepository getInstance() {
        return INSTANCE;
    }

    public void addCreateCardSeance(Long id, CreateCardSeance createCardSeance) {
        LOGGER.info("CreateCardSeance with id:{} was added to repository", id);
        createCardSeances.put(id, createCardSeance);
    }

    public boolean containsCreateCardSeance(Long id) {
        return createCardSeances.containsKey(id);
    }

    public CreateCardSeance getCreateCardSeanceById(Long id) throws ExamRepositoryException {
        if (createCardSeances.containsKey(id)) {
            LOGGER.info("CreateCardSeance with id: {} contains and got from repository", id);
            return createCardSeances.get(id);
        }
        LOGGER.warn("Repository doesn't have any createCardSeance with such Id");
        throw new ExamRepositoryException("Repository doesn't have any createCardSeance with such Id");
    }

    public void removeCreateCardSeanceById(Long id) throws ExamRepositoryException {
        if (createCardSeances.containsKey(id)) {
            createCardSeances.entrySet().removeIf(entry -> entry.getKey().equals(id));
            LOGGER.info("CreateCardSeance with id: {} was removed from repository", id);
        } else {
            LOGGER.warn("Repository doesn't have any createCardSeance with such Id to remove");
            throw new ExamRepositoryException("Repository doesn't have any createCardSeance with such Id to remove");
        }
    }

    public List<CreateCardSeance> getAll() throws ExamRepositoryException {
        List<CreateCardSeance> result = new ArrayList<>(createCardSeances.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any createCardSeance");
    }
}
