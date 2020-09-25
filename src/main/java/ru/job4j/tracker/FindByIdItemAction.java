package ru.job4j.tracker;

public class FindByIdItemAction implements UserAction {
    @Override
    public String name() {
        return "=== Find items by ID ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        System.out.println("Find item by id");
        String id = input.askStr("Please enter id");
        Item item = memTracker.findById(id);
        if (item != null) {
            System.out.println("Item found");
            System.out.println(item);
        } else {
            System.out.println("Item was not find");
        }
        return true;
    }
}
