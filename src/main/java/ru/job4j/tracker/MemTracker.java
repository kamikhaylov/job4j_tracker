package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MemTracker implements Store {
    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    @Override
    public void init() {

    }

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return items;
    }

    public List<Item> findByName(String key) {
        List<Item> itemsByKey = new ArrayList<>();
        for (int index = 0; index < items.size(); index++) {
            if (key.equals(items.get(index).getName())) {
                itemsByKey.add(items.get(index));
            }
        }
        return itemsByKey;
    }

    public boolean replace(int id, Item item) {
        boolean result = false;
         int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items.set(index, item);
            result = true;
        }
        return result;
    }

    public boolean delete(int id)  {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            items.remove(index);
            result = true;
        }
        return result;
    }

    private int indexOf(int id) {
        int result = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {
        MemTracker mem = new MemTracker();
        long time = System.currentTimeMillis();
        for (int i = 1; i < 1000000; i++) {
            new Item("item" + i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time);
    }
}