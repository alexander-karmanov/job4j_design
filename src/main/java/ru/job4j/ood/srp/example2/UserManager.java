package ru.job4j.ood.srp.example2;

public interface UserManager {
    void addUser(User user);
    void removeUser(User user);
    boolean checkUserAccess(User user);
}
