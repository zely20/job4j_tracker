package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {

    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {

            actions.forEach(action -> System.out.println(action.name()));
            int select = input.askInt("Select: ",6);
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Output output = new ConsoleOutput();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction());
        actions.add(new ShowAllAction());
        actions.add(new EditItemAction(output));
        actions.add(new DeleteItemAction(output));
        actions.add(new FindByIdItemAction(output));
        actions.add(new FindByNameItemsAction(output));

        try (Store tracker = new SqlTracker()) {
            tracker.init();
            new StartUI().init(validate, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
