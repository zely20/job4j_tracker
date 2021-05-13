package ru.job4j.tracker;

public class DeleteItemAction implements UserAction {

    private final Output output;

    public DeleteItemAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Delete item ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        String id = input.askStr("For delete item please enter ID ");
        if (memTracker.delete(id)) {
            output.println("Item deleted");
        } else {
            output.println("Item was not delete");
        }
        return  true;
    }
}
