package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.bean.test.TaskTest;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.EnglishCardRepository;
import by.vss.exam.repository.JavaCardRepository;
import by.vss.exam.repository.Repository;
import by.vss.exam.utill.Shuffler;
import com.fasterxml.jackson.databind.node.LongNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CardService {
    Repository<Card> javaCardRepository = JavaCardRepository.getInstance();
    Repository<Card> englishCardRepository = EnglishCardRepository.getInstance();

    public CardService() {

    }

    public boolean containsCard(Long id, CardType cardType) {
        if (cardType.equals(CardType.JAVA)) {
            return javaCardRepository.contains(id);
        } else if (cardType.equals(CardType.ENGLISH)) {
            return englishCardRepository.contains(id);
        }
        return false;
    }

//    public Card getCardOrCreate(Long id, CardType type) {
//        if (containsCard(id, type)) {
//            return getCard(id, type);
//        } else {
//            return createCard(type);
//        }
//    }

    public Card getCard(Long id, CardType type) {
        try {
            return getRepositoryByType(type).getById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new Card();
    }

    public Card createCard(CardType type, Long creatorId) {
        Repository<Card> repository = getRepositoryByType(type);
        Long id = repository.getLastGeneratedId() + 1;
        Card card = new Card();
        card.setCardType(type);
        card.setCardId(id);
        card.setCreatorId(creatorId);
        card.setApproved(false);
        repository.add(card);
        return card;
    }

    public List<Card> getAllCards(CardType type) {
        Repository<Card> repository = getRepositoryByType(type);
        List<Card> result = new ArrayList<>();
        try {
            result = repository.getAll();
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Card> getUserCards(Set<Long> userCards, CardType type) {
        Repository<Card> repository = getRepositoryByType(type);
        List<Card> result = new ArrayList<>(userCards.size());
        int repoSize = repository.getRepositorySize();
        for (long l = 0; l < repoSize; l++) {
            if (userCards.contains(l)) {
                try {
                    result.add(repository.getById(l));
                } catch (ExamRepositoryException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public List<Card> getShuffledApprovedCardsList(CardType type) {
        Repository<Card> repository = getRepositoryByType(type);
        int size = repository.getRepositorySize() - 1;   //todo -1  т.к. надо вычесть карту хранящую последний сгенерированный айди

        List<Long> list = Shuffler.getShuffledList(size, size);
        List<Card> cards = new ArrayList<>();

        try {
            for (Long l : list) {
                Card card = repository.getById(l);
                if (card.isApproved()) {
                    cards.add(repository.getById(l));
                }
            }
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
            return cards;
        }
        return cards;
    }

    private Repository<Card> getRepositoryByType(CardType type) {
        Repository<Card> repository = null;
        if (type.equals(CardType.JAVA)) {
            repository = javaCardRepository;
        } else if (type.equals(CardType.ENGLISH)) {
            repository = englishCardRepository;
        }
        return repository;
    }
}
