package ru.job4j.tracker;

public class FindByNameItemsAction implements UserAction {
    @Override
    public String name() {
        return "=== Find By Name Items ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        String name = input.askStr("Enter name: ");
        for (Item item : tracker.findByName(name)) {
            System.out.println(item);
        }
        return true;
    }
}
