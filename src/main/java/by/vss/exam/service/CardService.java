package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.JavaCardRepository;
import by.vss.exam.utill.Shuffler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardService {
    JavaCardRepository javaCardRepository = JavaCardRepository.getInstance();

    public CardService() {

    }

    public List<Card> getAllCards() {
        List<Card> result = new ArrayList<>();
        try {
            result = javaCardRepository.getAllCards();
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Card> getShuffledCardsList(int numberOfCards) {
        int size = javaCardRepository.getRepositorySize();

        if (numberOfCards > size) {
            numberOfCards = size;
        }

        List<Long> list = Shuffler.getShuffledList(numberOfCards, size);
        List<Card> cards = new ArrayList<>(numberOfCards);

        try {
            for (Long l : list) {
                cards.add(javaCardRepository.getCardById(l));
            }
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
            return cards;
        }
        return cards;
    }
}
