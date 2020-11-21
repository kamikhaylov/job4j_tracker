package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;

public class DeleteItemActions implements UserAction {
    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("=== Delete item ===");
        int id = input.askInt("Enter ID item: ");
        Boolean result = tracker.delete(id);
        if (result) {
            System.out.println("Operation completed successfully");
        } else {
            System.out.println("ID not found");
        }
        return true;
    }
}
