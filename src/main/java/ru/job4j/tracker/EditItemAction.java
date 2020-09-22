package ru.job4j.tracker;

public class EditItemAction implements UserAction {
    @Override
    public String name() {
        return "=== edit item ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("Please enter id ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(id, name);
        if (memTracker.replace(id,item)) {
            System.out.println("Item replaced");
        } else {
            System.out.println("Item was not replace");
        }
        return true;
    }
}
