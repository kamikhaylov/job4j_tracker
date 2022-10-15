package ru.job4j.tracker.actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class CreateActionTest {
    private static final String ITEM_NAME = "itemName";
    private static final String EXPECTED_SUCCESS = "=== Create a new Item ==="
            + System.lineSeparator();

    @Mock
    private Input input;

    @BeforeEach
    private void before() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    private void after() {
        Mockito.reset(input);
    }

    @Test
    public void whenCreateActionThenSuccess() {
        Store tracker = new MemTracker();
        Output out = new StubOutput();
        UserAction userAction = new CreateAction(out);

        Mockito.when(input.askInt(any(String.class))).thenReturn(1);
        Mockito.when(input.askStr(any(String.class))).thenReturn(ITEM_NAME);

        userAction.execute(input, tracker);
        List<Item> result = tracker.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), 1);
        Assertions.assertNotNull(result.get(0));
        Assertions.assertNotNull(result.get(0).getName());
        Assertions.assertEquals(result.get(0).getName(), ITEM_NAME);
        Assertions.assertEquals(out.toString(), EXPECTED_SUCCESS);
    }
}