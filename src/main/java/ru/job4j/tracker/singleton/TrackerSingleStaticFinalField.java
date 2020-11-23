package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

public class TrackerSingleStaticFinalField {
    private static final TrackerSingleStaticFinalField INSTANCE = new TrackerSingleStaticFinalField();

    private TrackerSingleStaticFinalField() {
    }

    public static TrackerSingleStaticFinalField getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleStaticFinalField tracker = TrackerSingleStaticFinalField.getInstance();
    }
}