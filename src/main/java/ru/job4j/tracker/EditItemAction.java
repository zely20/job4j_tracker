package ru.job4j.tracker;

public class EditItemAction implements UserAction {
    @Override
    public String name() {
        return "=== edit item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("Please enter id ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        return  (tracker.replace(id, item));
    }
}
