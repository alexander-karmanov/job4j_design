package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import ru.job4j.generics.Role;
import ru.job4j.generics.RoleStore;
import ru.job4j.generics.User;
import ru.job4j.generics.UserStore;

public class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        Role result = store.findById("7");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.add(new Role("1", "user"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenReplaceThenRolenameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("1", new Role("1", "manager"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("manager");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        store.replace("2", new Role("2", "manager"));
        Role result = store.findById("1");
        assertThat(result.getRolename()).isEqualTo("admin");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("2", "manager"));
        store.delete("2");
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRolenameIsAdmin() {
        UserStore store = new UserStore();
        store.add(new User("1", "admin"));
        store.delete("5");
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("admin");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean rsl = store.replace("1", new Role("1", "manager"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean rsl = store.replace("7", new Role("7", "manager"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "admin"));
        boolean rsl = store.delete("3");
        assertThat(rsl).isFalse();
    }
}
