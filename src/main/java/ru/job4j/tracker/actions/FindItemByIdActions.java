package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.output.Output;

public class FindItemByIdActions implements UserAction {
    private final Output out;

    public FindItemByIdActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find item by Id";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find item by Id ===");
        int id = input.askInt("Enter ID item: ");
        Item result = tracker.findById(id);
        if (result != null) {
            out.println(result);
        } else {
            out.println("Item with id " + id + " not found");
        }
        return true;
    }
}
