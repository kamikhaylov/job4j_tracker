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
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
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

    public boolean replace(int id, Item item) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            items[index].setName(item.getName());
            result = true;
        }
        return result;
    }

    public boolean delete(int id)  {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(items, index + 1, items, index, size - index);
            items[size - 1] = null;
            size--;
            result = true;
        }
        return result;
    }

    private int indexOf(int id) {
        int result = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }
}