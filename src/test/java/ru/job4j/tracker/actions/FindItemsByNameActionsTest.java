package ru.job4j.tracker.actions;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.mockito.ArgumentMatchers.any;

public class FindItemsByNameActionsTest {
    private static final String ITEM_NAME = "itemName";
    private static final String EXPECTED_SUCCESS = "=== Find items by name ==="
            + System.lineSeparator();
    private static final String EXPECTED_FAIL = "=== Find items by name ==="
            + System.lineSeparator()
            + "Item with id " + ITEM_NAME + " not found"
            + System.lineSeparator();

    @Mock
    private Input input;

    @BeforeClass
    private void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    private void after() {
        Mockito.reset(input);
    }

    @Test
    public void whenFindItemsByNameActionsThenSuccess() {
        Store tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction userAction = new FindItemsByNameActions(out);
        Item item = new Item(ITEM_NAME);
        tracker.add(item);

        Mockito.when(input.askInt(any(String.class))).thenReturn(1);
        Mockito.when(input.askStr(any(String.class))).thenReturn(ITEM_NAME);

        userAction.execute(input, tracker);

        Assert.assertEquals(out.toString(), EXPECTED_SUCCESS + item + System.lineSeparator());
    }

    @Test
    public void whenFindItemsByNameActionsThenFail() {
        Store tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction userAction = new FindItemsByNameActions(out);

        Mockito.when(input.askInt(any(String.class))).thenReturn(1);
        Mockito.when(input.askStr(any(String.class))).thenReturn(ITEM_NAME);

        userAction.execute(input, tracker);

        Assert.assertEquals(out.toString(), EXPECTED_FAIL);
    }
}