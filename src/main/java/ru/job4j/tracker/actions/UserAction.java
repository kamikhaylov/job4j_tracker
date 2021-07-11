package ru.job4j.tracker.actions;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.MemTracker;

public interface UserAction {
    String name();

    boolean execute(Input input, Store tracker);
}