package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        while (run) {
            this.showMenu();
            int select = Integer.valueOf(input.askStr("Select: "));
            if (select == 0) {
                System.out.println("=== Create a new Item ====");
                String name = input.askStr("Enter name: ");
                Item item = new Item(name);
                tracker.add(item);
            } else if (select == 1) {
                System.out.println("Show all items");
                for (Item item : tracker.findAll()) {
                    System.out.println(item);
                }
            } else if (select == 2) {
                System.out.println("Edit item");
                String id = input.askStr("Please enter id");
                if (tracker.findById(id) != null) {
                    System.out.print("Enter name: ");
                    String name = input.askStr("Enter name: ");
                    Item item = new Item(name);
                    tracker.replace(id, item);
                    System.out.println("Item is changed");
                } else {
                    System.out.println("Wrong id " + id);
                }
            } else if (select == 3) {

                String id = input.askStr("For delete item please enter ID ");
                if (tracker.delete(id)) {
                    System.out.println("Item with " + id + " is deleted");
                } else {
                    System.out.println("Wrong id " + id);
                }
            } else if (select == 4) {
                System.out.println("Find item by id");
                String id = input.askStr("Please enter id");
                Item item = tracker.findById(id);
                if (item != null){
                    System.out.println("By id was find item with name " + item.getName());
                } else {
                    System.out.println("Wrong id!");
                }
            } else  if (select == 5){
                System.out.println("Find items by name");
                String name = input.askStr("Enter name: ");
                for (Item item: tracker.findByName(name)) {
                    System.out.println(item);
                }
            } else if (select == 6) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
    }
}
