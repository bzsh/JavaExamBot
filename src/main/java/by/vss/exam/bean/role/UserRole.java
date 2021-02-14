package by.vss.exam.bean.role;

public enum UserRole {
    COLLABORATOR,    // can pass tests, can create new Tasks/Cards, and send it to moderate
    ADMIN,           // can pass tests, admin can see list of all tasks/cards/users, can see comments, edit Tasks/cards/users
    USER             // can pass tests and write comments to Tasks, can see your statistic
}
