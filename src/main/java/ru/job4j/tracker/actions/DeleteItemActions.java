package ru.job4j.tracker.actions;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class DeleteItemActions implements UserAction {
    private final Output out;

    public DeleteItemActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete item ===");
        int id = input.askInt("Enter ID item: ");
        Boolean result = tracker.delete(id);
        if (result) {
            out.println("Operation completed successfully");
        } else {
            out.println("ID not found");
        }
        return true;
    }
}
