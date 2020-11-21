package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindItemsByNameActions implements UserAction {
    @Override
    public String name() {
        return "=== Find items by name ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name item: ");
        Item[] result = tracker.findByName(name);
        if (result.length > 0) {
            for (Item item : result) {
                System.out.println(item);
            }
        } else {
            System.out.println("Item with id " + name + " not found");
        }
        return true;
    }
}
