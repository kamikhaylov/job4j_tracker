package ru.job4j.tracker.singleton;

import org.junit.Test;
import static org.junit.Assert.assertSame;

public class TrackerSingleTest {
    @Test
    public void whenTrackerSingleStaticFinalField() {
        TrackerSingleStaticFinalField tracker1 = TrackerSingleStaticFinalField.getInstance();
        TrackerSingleStaticFinalField tracker2 = TrackerSingleStaticFinalField.getInstance();
        assertSame(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingleStaticFinalClass() {
        TrackerSingleStaticFinalClass tracker1 = TrackerSingleStaticFinalClass.getInstance();
        TrackerSingleStaticFinalClass tracker2 = TrackerSingleStaticFinalClass.getInstance();
        assertSame(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingleStaticField() {
        TrackerSingleStaticField tracker1 = TrackerSingleStaticField.getInstance();
        TrackerSingleStaticField tracker2 = TrackerSingleStaticField.getInstance();
        assertSame(tracker1, tracker2);
    }

    @Test
    public void whenTrackerSingleEnum() {
        TrackerSingleEnum tracker1 = TrackerSingleEnum.INSTANCE;
        TrackerSingleEnum tracker2 = TrackerSingleEnum.INSTANCE;
        assertSame(tracker1, tracker2);
    }
}