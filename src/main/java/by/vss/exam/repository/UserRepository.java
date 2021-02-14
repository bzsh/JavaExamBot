package by.vss.exam.repository;

import by.vss.exam.bean.User;
import by.vss.exam.constant.ConstantHolder;
import by.vss.exam.exception.ExamRepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private static final UserRepository INSTANCE = new UserRepository();
    private final ResourceBundle bundle = ResourceBundle.getBundle(ConstantHolder.BOT_PROPERTIES);
    private HashMap<Long, User> users;

    private UserRepository() {
        users = new HashMap<>();
    }

    public static UserRepository getInstance() {
        return INSTANCE;
    }

    public void addUser(Long id, User user) {
        users.put(id, user);
    }

    public boolean containsUser(Long id) {
        return users.containsKey(id);
    }

    public User getUserById(Long id) throws ExamRepositoryException {
        if (users.containsKey(id)) {
            return users.get(id);
        }
        LOGGER.warn("Repository doesn't have any users with such Id");
        throw new ExamRepositoryException("Repository doesn't have any users with such Id");
    }

    public List<User> getAll() throws ExamRepositoryException {
        List<User> result = new ArrayList<>(users.values());
        if (!result.isEmpty()) {
            return result;
        }
        throw new ExamRepositoryException("Repository doesn't have any user");
    }

    public void removeUserById(Long id) throws ExamRepositoryException {
        if (users.containsKey(id)) {
            users.entrySet().removeIf(entry -> entry.getKey().equals(id));
        } else {
            throw new ExamRepositoryException("Repository doesn't have any user to remove");
        }
    }
}
