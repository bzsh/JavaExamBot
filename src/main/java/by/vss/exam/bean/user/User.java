package by.vss.exam.bean.user;

import by.vss.exam.bean.user.statistic.Statistic;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private UserRole role;
    private Statistic userStatistic = new Statistic();

    public User(Long userId, String firstName, String lastName, String userName, UserRole role, Statistic userStatistic) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
        this.userStatistic = userStatistic;
    }

    public User(Long userId, String firstName, String lastName, String userName, UserRole role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.role = role;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Statistic getUserStatistic() {
        return userStatistic;
    }

    public void setUserStatistic(Statistic userStatistic) {
        this.userStatistic = userStatistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && role == user.role && Objects.equals(userStatistic, user.userStatistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userName, role, userStatistic);
    }
}
