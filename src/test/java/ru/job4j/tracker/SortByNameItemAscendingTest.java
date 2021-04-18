package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SortByNameItemAscendingTest {
    @Test
    public void whenSortByNameItemAscending() {
        List<Item> items = Arrays.asList(
                new Item("3"),
                new Item("1"),
                new Item("2")
        );
        List<Item> expected  = Arrays.asList(
                new Item("1"),
                new Item("2"),
                new Item("3")
        );
        Collections.sort(items, new SortByNameItemAscending());
        assertThat(items, is(expected));
    }

    @Test
    public void whenSortByNameItemAscendingTracker() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("3");
        Item item2 = new Item("1");
        Item item3 = new Item("2");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> items = tracker.findAll();
        List<Item> expected  = Arrays.asList(item2, item3, item1);
        Collections.sort(items, new SortByNameItemAscending());
        assertThat(items, is(expected));
    }
}