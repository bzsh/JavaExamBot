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

public class EnglishCardRepository implements Repository<Card> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnglishCardRepository.class);
    private static final EnglishCardRepository INSTANCE = new EnglishCardRepository();
    private final UniversalJsonReaderWriter<Card> cardReaderWriter = new UniversalJsonReaderWriter<>(ConstantHolder.ENG_CARD_PATH, Card.class);

    private final HashMap<Long, Card> cards = cardReaderWriter.getAllFromFile();
    private Long lastGeneratedId = cards.get(ConstantHolder.LAST_GENERATED_ID_CARD).getCardId();

    private EnglishCardRepository() {
    }

    public static EnglishCardRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Card card) {
        Long id = lastGeneratedId + 1;
        cards.put(id, card);
        cards.get(ConstantHolder.LAST_GENERATED_ID_CARD).setCardId(id);
        lastGeneratedId = cards.get(ConstantHolder.LAST_GENERATED_ID_CARD).getCardId();
    }

    @Override
    public int getRepositorySize() {
        int repoSize = cards.size();
        LOGGER.info("getRepositorySize method was called and returned size: {}.", repoSize);
        return repoSize;
    }

    @Override
    public List<Card> getAll() throws ExamRepositoryException {
        List<Card> result = new ArrayList<>(cards.values());
        if (!result.isEmpty()) {
            return result;
        }
        LOGGER.warn("Repository doesn't have any cards");
        throw new ExamRepositoryException("Repository doesn't have any cards");
    }

    @Override
    public Card getById(Long id) throws ExamRepositoryException {
        if (cards.containsKey(id)) {
            LOGGER.info("getCardById method was called, task is found and returned.");
            return cards.get(id);
        }
        LOGGER.warn("Repository doesn't have any cards with such Id");
        throw new ExamRepositoryException("Repository doesn't have any cards with such Id");
    }

    @Override
    public void removeById(Long id) throws ExamRepositoryException {
        if (cards.containsKey(id)) {
            cards.entrySet().removeIf(entry -> entry.getKey().equals(id));                     //TODO ????????????
        } else {
            throw new ExamRepositoryException("Repository doesn't have any cards to remove");
        }
    }

    @Override
    public void saveAllToFile() {
        System.out.println(cards.size());
        cardReaderWriter.addAllToFile(cards);
    }

    @Override
    public boolean contains(Long id) {
        return cards.containsKey(id);
    }

    @Override
    public Long getLastGeneratedId() {
        return lastGeneratedId;
    }
}
