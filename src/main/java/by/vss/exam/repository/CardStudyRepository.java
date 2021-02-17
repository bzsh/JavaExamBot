package by.vss.exam.repository;

import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardStudyRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(CardStudyRepository.class);
    private static final CardStudyRepository INSTANCE = new CardStudyRepository();
    private HashMap<Long, CardStudy> studies;

    private CardStudyRepository() {
        studies = new HashMap<>();
    }

    public static CardStudyRepository getInstance() {
        return INSTANCE;
    }

    public void addStudy(Long id, CardStudy study) {
        LOGGER.info("Study with id:{} was added to repository", id);
        studies.put(id, study);
    }

    public boolean containsStudy(Long id) {
        return studies.containsKey(id);
    }

    public CardStudy getStudyById(Long id) throws ExamRepositoryException {
        if (studies.containsKey(id)) {
            LOGGER.info("Study with id: {} contains and got from repository", id);
            return studies.get(id);
        }
        LOGGER.warn("Repository doesn't have any studies with such Id");
        throw new ExamRepositoryException("Repository doesn't have any studies with such Id");
    }

    public void removeStudyById(Long id) throws ExamRepositoryException {
        if (studies.containsKey(id)) {
            studies.entrySet().removeIf(entry -> entry.getKey().equals(id));
            LOGGER.info("Study with id: {} was removed from repository", id);
        } else {
            LOGGER.warn("Repository doesn't have any studies with such Id to remove");
            throw new ExamRepositoryException("Repository doesn't have any studies with such Id to remove");
        }
    }

    public List<CardStudy> getAll() throws ExamRepositoryException {
        List<CardStudy> result = new ArrayList<>(studies.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any studies");
    }

}
