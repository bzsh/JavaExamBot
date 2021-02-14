package by.vss.exam.service;

import by.vss.exam.bean.User;
import by.vss.exam.bean.role.UserRole;
import by.vss.exam.exception.ExamRepositoryException;
import by.vss.exam.repository.UserRepository;

public class UserService {
    UserRepository userRepository = UserRepository.getInstance();

    public User getUser(Long id) {
        return getUserFromRepository(id);
    }

    public User getUserOrCreate(Long id, String firstName, String lastName, String userName, UserRole role) {
        if (containsUser(id)) {
            return getUserFromRepository(id);
        } else {
            User user = new User(id, firstName, lastName, userName, role);
            userRepository.addUser(id, user);
            return user;
        }
    }

    private boolean containsUser(Long id) {
        return userRepository.containsUser(id);
    }

    private User getUserFromRepository(Long id) {
        try {
            return userRepository.getUserById(id);
        } catch (ExamRepositoryException e) {
            e.printStackTrace();
        }
        return new User();
    }
}
