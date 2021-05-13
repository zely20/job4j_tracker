package ru.job4j.tracker;

public class FindByNameItemsAction implements UserAction {

    private final Output output;

    public FindByNameItemsAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find By Name Items ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String name = input.askStr("Enter name: ");
        for (Item item : memTracker.findByName(name)) {
            output.println(item);
        }
        return true;
    }
}
