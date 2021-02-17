package by.vss.exam.service;

import by.vss.exam.bean.Card;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.JavaCardRepository;
import by.vss.exam.utill.Shuffler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

    public List<Card> getUserCards(Set<Long> userCards) {
        List<Card> result = new ArrayList<>(userCards.size());
        int repoSize = javaCardRepository.getRepositorySize();
        for (long l = 0; l < repoSize; l++){
            if(userCards.contains(l)){
                try {
                    result.add(javaCardRepository.getCardById(l));
                }catch(ExamRepositoryException e){
                    e.printStackTrace();
                }
            }
        }
            return result;
    }

    public List<Card> getShuffledCardsList() {
        int size = javaCardRepository.getRepositorySize();

        List<Long> list = Shuffler.getShuffledList(size, size);
        List<Card> cards = new ArrayList<>(size);

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
