package ru.job4j.ood.lsp.storage;

import java.time.LocalDate;

public class Butter extends Food {
    public Butter(String name, LocalDate createDate, LocalDate expiryDate, double price) {
        super(name, createDate, expiryDate, price);
    }
}
