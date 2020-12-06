package ru.job4j.tracker;

import ru.job4j.tracker.Item;

import java.util.Comparator;

public class SortByNameItemDescending implements Comparator<Item> {
    @Override
    public int compare(Item first, Item second) {
        return second.getName().compareTo(first.getName());
    }
}