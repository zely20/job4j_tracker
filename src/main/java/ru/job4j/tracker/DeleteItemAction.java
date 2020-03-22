package ru.job4j.tracker;

public class DeleteItemAction implements UserAction {
    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String id = input.askStr("For delete item please enter ID ");
        return  (tracker.delete(id));
    }
}
