package ru.job4j.ood.srp.example2;

import java.util.List;

public class SimpleUserManager implements UserManager {

    private List<User> users;

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public boolean checkUserAccess(User user) {
        return false;
    }
}
