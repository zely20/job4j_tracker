package ru.job4j.tracker;

public class FindByIdItemAction implements UserAction {

    private final Output output;

    public FindByIdItemAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Find items by ID ====";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        System.out.println("Find item by id");
        Integer id = input.askInt("Please enter id");
        Item item = memTracker.findById(id);
        if (item != null) {
            output.println("Item found");
            output.println(item);
        } else {
            output.println("Item was not find");
        }
        return true;
    }
}
