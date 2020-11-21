package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class ReplaceActions implements UserAction {
    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Edit item ===");
        int id = input.askInt("Enter ID item: ");
        String newName = input.askStr("Enter new name: ");
        Item newItem = new Item(newName);
        Boolean result = tracker.replace(id, newItem);
        if (result) {
            System.out.println("Operation completed successfully");
        } else {
            System.out.println("ID not found");
        }
        return true;
    }
}
