package by.vss.exam.bean;

import by.vss.exam.bean.role.UserRole;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userName;
    private UserRole role;
    private Statistic UserStatistic;

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

    public Statistic getStatistics() {
        return UserStatistic;
    }

    public void setUserStatistic(Statistic userStatistic) {
        this.UserStatistic = userStatistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && role == user.role && Objects.equals(UserStatistic, user.UserStatistic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userName, role, UserStatistic);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }
}
