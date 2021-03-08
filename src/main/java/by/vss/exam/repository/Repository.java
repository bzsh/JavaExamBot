package by.vss.exam.repository;

import by.vss.exam.exception.ExamRepositoryException;

import java.util.List;

public interface Repository<T> {
    void add( T t);

    int getRepositorySize();

    boolean contains(Long id);

    List<T> getAll() throws ExamRepositoryException;

    T getById(Long id) throws ExamRepositoryException;

    void removeById(Long id) throws ExamRepositoryException;

    void saveAllToFile();

    Long getLastGeneratedId();

}
