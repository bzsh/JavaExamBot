package by.vss.exam.exception;

public class ExamRepositoryException extends Exception{
    public ExamRepositoryException(String message) {
        super(message);
    }

    public ExamRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExamRepositoryException(Throwable cause) {
        super(cause);
    }
}
