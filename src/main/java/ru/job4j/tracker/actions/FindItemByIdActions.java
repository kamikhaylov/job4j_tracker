package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class FindItemByIdActions implements UserAction {
    @Override
    public String name() {
        return "=== Find item by Id ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Find item by Id ===");
        int id = input.askInt("Enter ID item: ");
        Item result = tracker.findById(id);
        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Item with id " + id + " not found");
        }
        return true;
    }
}
