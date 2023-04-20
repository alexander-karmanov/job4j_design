package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        HashMap<Integer, User> users = new HashMap<>();

        for (User p : previous) {
            users.put(p.getId(), p);
        }
        for (User c : current) {
            if (!users.containsKey(c.getId())) {
                added++;
            }
            if (!users.containsValue(c) && users.containsKey(c.getId())) {
                changed++;
            }
            users.remove(c.getId());
        }
        deleted = users.size();
        return new Info(added, changed, deleted);
    }
}
