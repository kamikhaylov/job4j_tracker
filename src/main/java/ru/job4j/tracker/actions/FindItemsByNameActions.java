package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.output.Output;

public class FindItemsByNameActions implements UserAction {
    private final Output out;

    public FindItemsByNameActions(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find items by name";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Find items by name ===");
        String name = input.askStr("Enter name item: ");
        Item[] result = tracker.findByName(name);
        if (result.length > 0) {
            for (Item item : result) {
                out.println(item);
            }
        } else {
            out.println("Item with id " + name + " not found");
        }
        return true;
    }
}
