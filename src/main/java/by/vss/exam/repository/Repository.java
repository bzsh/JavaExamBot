package by.vss.exam.repository;

import by.vss.exam.exception.ExamRepositoryException;

import java.util.List;

public interface Repository<T> {
    void add(Long id, T t);

    int getRepositorySize();

    List<T> getAll() throws ExamRepositoryException;

    T getById(Long id) throws ExamRepositoryException;

    void removeById(Long id) throws ExamRepositoryException;

    void saveAllToFile();
}
