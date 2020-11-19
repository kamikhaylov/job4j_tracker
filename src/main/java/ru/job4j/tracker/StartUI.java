package ru.job4j.tracker;

public class StartUI {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = Integer.valueOf(input.askStr("Select: "));
            if (select == 0) {
                System.out.println("=== Create a new Item ===");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("=== Show all items ===");
                Item[] items = tracker.findAll();
                for (Item item : items) {
                    System.out.println(item);
                }
            } else if (select == 2) {
                System.out.println("=== Edit item ===");
                int id = input.askInt("Enter ID item: ");
                String newName = input.askStr("Enter new name: ");
                Item newItem = new Item(newName);
                Boolean result = tracker.replace(id, newItem);
                if (result) {
                    System.out.println("Operation completed successfully");
                } else {
                    System.out.println("ID not found");
                }
            } else if (select == 3) {
                System.out.println("=== Delete item ===");
                int id = input.askInt("Enter ID item: ");
                Boolean result = tracker.delete(id);
                if (result) {
                    System.out.println("Operation completed successfully");
                } else {
                    System.out.println("ID not found");
                }
            } else if (select == 4) {
                System.out.println("=== Find item by Id ===");
                int id = input.askInt("Enter ID item: ");
                Item result = tracker.findById(id);
                if (result != null) {
                    System.out.println(result);
                } else {
                    System.out.println("Item with id " + id + " not found");
                }
            } else if (select == 5) {
                System.out.println("=== Find items by name ===");
                String name = input.askStr("Enter name item: ");
                Item[] result = tracker.findByName(name);
                if (result.length > 0) {
                    for (Item item : result) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("Item with id " + name + " not found");
                }
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }
}