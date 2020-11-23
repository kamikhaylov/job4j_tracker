package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

public class TrackerSingleStaticFinalClass {
    private TrackerSingleStaticFinalClass() {
    }

    public static TrackerSingleStaticFinalClass getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static final class Holder {
        private static final TrackerSingleStaticFinalClass INSTANCE = new TrackerSingleStaticFinalClass();
    }

    public static void main(String[] args) {
        TrackerSingleStaticFinalClass tracker = TrackerSingleStaticFinalClass.getInstance();
    }
}