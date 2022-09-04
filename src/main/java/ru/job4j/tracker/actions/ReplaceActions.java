package ru.job4j.tracker.actions;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;

public class ReplaceActions implements UserAction {
    private final Output out;

    public ReplaceActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Edit item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Edit item ===");
        int id = input.askInt("Enter ID item: ");
        String newName = input.askStr("Enter new name: ");
        Item newItem = new Item(newName);
        Boolean result = tracker.replace(id, newItem);
        if (result) {
            out.println("Operation completed successfully");
        } else {
            out.println("ID not found");
        }
        return true;
    }
}
