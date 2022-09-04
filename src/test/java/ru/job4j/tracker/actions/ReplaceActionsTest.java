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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ReplaceActionsTest {
    private static final String ITEM_NAME = "itemName";
    private static final String EXPECTED_SUCCESS = "=== Edit item ==="
            + System.lineSeparator()
            + "Operation completed successfully"
            + System.lineSeparator();
    private static final String EXPECTED_FAIL = "=== Edit item ==="
            + System.lineSeparator()
            + "ID not found"
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
    public void whenReplaceActionsThenSuccess() {
        Store tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction userAction = new ReplaceActions(out);
        tracker.add(new Item(ITEM_NAME));

        Mockito.when(input.askInt(any(String.class))).thenReturn(1);
        Mockito.when(input.askStr(any(String.class))).thenReturn(ITEM_NAME);

        userAction.execute(input, tracker);
        List<Item> result = tracker.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Assert.assertNotNull(result.get(0));
        Assert.assertNotNull(result.get(0).getName());
        Assert.assertEquals(result.get(0).getName(), ITEM_NAME);
        Assert.assertEquals(out.toString(), EXPECTED_SUCCESS);
    }

    @Test
    public void whenReplaceActionsThenFail() {
        Store tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction userAction = new ReplaceActions(out);
        tracker.add(new Item(ITEM_NAME));

        userAction.execute(input, tracker);
        List<Item> result = tracker.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Assert.assertNotNull(result.get(0));
        Assert.assertNotNull(result.get(0).getName());
        Assert.assertEquals(result.get(0).getName(), ITEM_NAME);
        Assert.assertEquals(out.toString(), EXPECTED_FAIL);
    }
}