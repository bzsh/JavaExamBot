package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.CardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardService {
    CardRepository cardRepository = CardRepository.getInstance();

    public CardService() {

    }

    public List<Card> getAllCards() {
        List<Card> result = new ArrayList<>();
        try {
            result = cardRepository.getAllCards();
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Card> getShuffledCardsList(int numberOfCards) {
        int size = cardRepository.getRepositorySize();

        if (numberOfCards > size) {
            numberOfCards = size;
        }

        List<Long> list = getShuffledList(numberOfCards, size);
        List<Card> cards = new ArrayList<>(numberOfCards);

        try {
            for (Long l : list) {
                cards.add(cardRepository.getCardById(l));
            }
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
            return cards;
        }
        return cards;
    }

    private static ArrayList<Long> getShuffledList(int numberOfCards, int size) {
        ArrayList<Long> numbersGenerated = new ArrayList<>();                         // TODO !!!!

        for (int i = 0; i < numberOfCards; i++) {
            Random randNumber = new Random();
            long iNumber = randNumber.nextInt(size) + 1;

            if (!numbersGenerated.contains(iNumber)) {
                numbersGenerated.add(iNumber);
            } else {
                i--;
            }
        }
        return numbersGenerated;
    }
}
