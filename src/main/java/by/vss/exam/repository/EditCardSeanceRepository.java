package by.vss.exam.repository;

import by.vss.exam.bean.manage.editCard.EditCardSeance;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditCardSeanceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCardSeanceRepository.class);
    private static final EditCardSeanceRepository INSTANCE = new EditCardSeanceRepository();
    private HashMap<Long, EditCardSeance> editCardSeances;

    private EditCardSeanceRepository() {
        editCardSeances = new HashMap<>();
    }

    public static EditCardSeanceRepository getInstance() {
        return INSTANCE;
    }

    public void addEditCardSeance(Long id, EditCardSeance editCardSeance) {
        LOGGER.info("EditCardSeance with id:{} was added to repository", id);
        editCardSeances.put(id, editCardSeance);
    }

    public boolean containsEditCardSeance(Long id) {
        return editCardSeances.containsKey(id);
    }

    public EditCardSeance getEditCardSeanceById(Long id) throws ExamRepositoryException {
        if (editCardSeances.containsKey(id)) {
            LOGGER.info("EditCardSeance with id: {} contains and got from repository", id);
            return editCardSeances.get(id);
        }
        LOGGER.warn("Repository doesn't have any editCardSeances with such Id");
        throw new ExamRepositoryException("Repository doesn't have any editCardSeances with such Id");
    }

    public void removeEditCardSeanceById(Long id) throws ExamRepositoryException {
        if (editCardSeances.containsKey(id)) {
            editCardSeances.entrySet().removeIf(entry -> entry.getKey().equals(id));
            LOGGER.info("EditCardSeance with id: {} was removed from repository", id);
        } else {
            LOGGER.warn("Repository doesn't have any editCardSeances with such Id to remove");
            throw new ExamRepositoryException("Repository doesn't have any editCardSeances with such Id to remove");
        }
    }

    public List<EditCardSeance> getAll() throws ExamRepositoryException {
        List<EditCardSeance> result = new ArrayList<>(editCardSeances.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any editCardSeances");
    }
}
