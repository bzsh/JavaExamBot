package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.study.CardStudy;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.CardStudyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;

public class CardStudyService {
    public static final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    public static final Logger LOGGER = LoggerFactory.getLogger(CardStudyService.class);
    CardStudyRepository cardStudyRepository = CardStudyRepository.getInstance();
    CardService cardService = new CardService();

    public boolean containsStudy(Long id) {
        return cardStudyRepository.containsStudy(id);
    }

    public CardStudy getStudyOrCreate(Long id) {
        if (containsStudy(id)) {
            LOGGER.info("Study with id: {} was found and returned ", id);
            return getStudy(id);
        } else {
            LOGGER.info("Study with id: {} was created and returned ", id);
            return createStudy(id);
        }
    }

    public void doStudyAsNew(CardStudy oldStudy, CardType type) {
        List<Card> cards = cardService.getShuffledApprovedCardsList(type);
        oldStudy.setCurrentCardIndex(0);
        oldStudy.setNewCards(cards);
        oldStudy.setNew(true);
        oldStudy.setActive(false);
    }

    public CardStudy getStudy(Long id) {
        try {
            return cardStudyRepository.getStudyById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new CardStudy(id);
    }

    private CardStudy createStudy(Long id) {
        CardStudy study = new CardStudy(id);
        cardStudyRepository.addStudy(id, study);
        return study;
    }
}
