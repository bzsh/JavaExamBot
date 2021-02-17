package by.vss.exam.repository;

import by.vss.exam.bean.Card;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.json.UniversalJsonReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JavaCardRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaCardRepository.class);
    private static final JavaCardRepository INSTANCE = new JavaCardRepository();
    private final UniversalJsonReaderWriter<Card> cardReaderWriter = new UniversalJsonReaderWriter<>(ConstantHolder.JAVA_CARD_PATH, Card.class);

    private final HashMap<Long, Card> cards = cardReaderWriter.getAllFromFile();
    private Long lastGeneratedId;

    private JavaCardRepository() {
    }

    public static JavaCardRepository getInstance() {
        return INSTANCE;
    }

    public void addCard(Long id, Card card) {
        cards.put(id, card);
    }

    public int getRepositorySize() {
        int repoSize = cards.size();
        LOGGER.info("getRepositorySize method was called and returned size: {}.", repoSize);
        return repoSize;
    }

    public List<Card> getAllCards() throws ExamRepositoryException {
        List<Card> result = new ArrayList<>(cards.values());
        if (!result.isEmpty()) {
            return result;
        }
        LOGGER.warn("Repository doesn't have any cards");
        throw new ExamRepositoryException("Repository doesn't have any cards");
    }

    public Card getCardById(Long id) throws ExamRepositoryException {
        if (cards.containsKey(id)) {
            LOGGER.info("getCardById method was called, task is found and returned.");
            return cards.get(id);
        }
        LOGGER.warn("Repository doesn't have any cards with such Id");
        throw new ExamRepositoryException("Repository doesn't have any cards with such Id");
    }

    public void removeCardById(Long id) throws ExamRepositoryException {
        if (cards.containsKey(id)) {
            cards.entrySet().removeIf(entry -> entry.getKey().equals(id));                     //TODO ????????????
        } else {
            throw new ExamRepositoryException("Repository doesn't have any cards to remove");
        }
    }
}
