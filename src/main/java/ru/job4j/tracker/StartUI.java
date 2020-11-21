package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.input.Input;

public class StartUI {

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        UserAction[] actions = {new CreateAction(),
                                new ShowAllAction(),
                                new ReplaceActions(),
                                new DeleteItemActions(),
                                new FindItemByIdActions(),
                                new FindItemsByNameActions(),
                                new ExitActions()};
        new StartUI().init(input, tracker, actions);
    }

    public void init(Input input, Tracker tracker, UserAction[] actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            UserAction action = actions[select];
            run = action.execute(input, tracker);
        }
    }

    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }
}