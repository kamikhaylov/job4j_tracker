package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

public class TrackerSingleStaticField {
    private static TrackerSingleStaticField instance;

    private TrackerSingleStaticField() {
    }

    public static TrackerSingleStaticField getInstance() {
        if (instance == null) {
            instance = new TrackerSingleStaticField();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }
}