package ru.job4j.tracker;

public class EditItemAction implements UserAction {

    private final Output output;

    public EditItemAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== edit item ===";
    }

    @Override
    public boolean execute(Input input, Store memTracker) {
        Integer id = input.askInt("Please enter id ");
        String name = input.askStr("Enter name: ");
        Item item = new Item(id, name);
        if (memTracker.replace(id,item)) {
            output.println("Item replaced");
        } else {
            output.println("Item was not replace");
        }
        return true;
    }
}
