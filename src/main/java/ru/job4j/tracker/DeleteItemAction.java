package ru.job4j.tracker;

public class DeleteItemAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("For delete item please enter ID ");
        if (memTracker.delete(id)) {
            System.out.println("Item deleted");
        } else {
            System.out.println("Item was not delete");
        }
        return  true;
    }
}
