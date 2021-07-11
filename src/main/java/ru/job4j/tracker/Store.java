package ru.job4j.tracker;

import java.util.List;

public interface Store extends AutoCloseable {
    void init();

    Item add(Item item);

    Item findById(int id);

    List<Item> findAll();

    List<Item> findByName(String key);

    boolean replace(int id, Item item);

    boolean delete(int id);
}