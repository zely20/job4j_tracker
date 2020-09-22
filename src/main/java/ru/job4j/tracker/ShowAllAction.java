package ru.job4j.tracker;

public class ShowAllAction implements UserAction {
    @Override
    public String name() {
        return "=== Show all items ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        for (Item item : memTracker.findAll()) {
            System.out.println(item);
        }
            return true;
    }
}
