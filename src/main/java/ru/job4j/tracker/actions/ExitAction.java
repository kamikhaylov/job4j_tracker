package ru.job4j.tracker.actions;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.MemTracker;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        return false;
    }
}
