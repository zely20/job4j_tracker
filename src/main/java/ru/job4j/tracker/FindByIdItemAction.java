package ru.job4j.tracker;

public class FindByIdItemAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by ID ====";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        System.out.println("Find item by id");
        String id = input.askStr("Please enter id");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Item found");
        } else {
            System.out.println("Item was not find");
        }
        return true;
    }
}
