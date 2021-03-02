package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.bean.CardType;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.EnglishCardRepository;
import by.vss.exam.repository.JavaCardRepository;
import by.vss.exam.repository.Repository;
import by.vss.exam.utill.Shuffler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CardService {
    Repository<Card> javaCardRepository = JavaCardRepository.getInstance();
    Repository<Card> englishCardRepository = EnglishCardRepository.getInstance();

    public CardService() {

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

    public List<Card> getShuffledCardsList(CardType type) {
        Repository<Card> repository = getRepositoryByType(type);
        int size = repository.getRepositorySize();

        List<Long> list = Shuffler.getShuffledList(size, size);
        List<Card> cards = new ArrayList<>(size);

        try {
            for (Long l : list) {
                cards.add(repository.getById(l));
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
