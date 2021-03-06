package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.output.ConsoleOutput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

public class StartUITest {
    @Test
    public void whenAddItem() {
        String[] answers = {"Fix PC"};
        Output output = new ConsoleOutput();
        Input input = new StubInput(answers);
        MemTracker memTracker = new MemTracker();
        CreateAction createAction = new CreateAction(output);
        createAction.execute(input, memTracker);
        Item created = memTracker.findAll().get(0);
        Item expected = new Item("Fix PC");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new ConsoleOutput();
        Item item = new Item("new item");
        memTracker.add(item);
        String[] answers = {String.valueOf(item.getId()), "replaced item"};
        ReplaceActions replaceActions = new ReplaceActions(output);
        replaceActions.execute(new StubInput(answers), memTracker);
        Item replaced = memTracker.findById(item.getId());
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public void whenDeleteItem() {
        MemTracker memTracker = new MemTracker();
        Output output = new ConsoleOutput();
        Item item = new Item("new item");
        memTracker.add(item);
        String[] answers = {String.valueOf(item.getId())};
        DeleteItemActions deleteItemActions = new DeleteItemActions(output);
        deleteItemActions.execute(new StubInput(answers), memTracker);
        Item deleted = memTracker.findById(item.getId());
        assertNull(deleted);
    }

    @Test
    public void whenCreateItemMenu() {
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    public void whenReplaceItemMenu() {
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "New item name", "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceActions(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItemMenu() {
        Store tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteItemActions(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenShowAllAction() {
        Store tracker = new MemTracker();
        Output output = new StubOutput();
        Input in = new StubInput(new String[]{"0", "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAllAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Show all items ===" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Show all items" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenFindItemsByNameActions() {
        Store tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = tracker.add(new Item("Find items by name"));
        Input in = new StubInput(new String[]{"0", item.getName(), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemsByNameActions(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find items by name ===" + System.lineSeparator()
                        + item + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find items by name" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void findItemByIdActions() {
        Store tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = tracker.add(new Item("Find item by Id"));
        Input in = new StubInput(new String[]{"0", String.valueOf(item.getId()), "1"});
        List<UserAction> actions = new ArrayList<>();
        actions.add(new FindItemByIdActions(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Find item by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
                        + "=== Find item by Id ===" + System.lineSeparator()
                        + item + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. Find item by Id" + System.lineSeparator()
                        + "1. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenExit() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(output.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit" + System.lineSeparator()
        ));
    }

    @Test
    public void whenInvalidExit() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[]{"9", "0"}
        );
        Store tracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(output.toString(), is(
                String.format(
                                "Menu.%n"
                                + "0. Exit%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. Exit%n"
                )
        ));
    }
}