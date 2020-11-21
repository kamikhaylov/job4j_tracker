package ru.job4j.tracker.actions;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Tracker;

public class ExitAction implements UserAction {
    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        return false;
    }
}
