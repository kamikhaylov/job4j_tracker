package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item result = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Item[] findAll() {
        return Arrays.copyOf(items, size);
    }

    public Item[] findByName(String key) {
        Item[] itemsByKey = new Item[size];
        int newIndex = 0;
        for (int index = 0; index < itemsByKey.length; index++) {
            if (key.equals(items[index])) {
                itemsByKey[newIndex] = items[index];
                newIndex++;
            }
        }
        return Arrays.copyOf(itemsByKey, newIndex + 1);
    }
}