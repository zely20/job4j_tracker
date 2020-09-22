package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
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
        try (Store tracker = new SqlTracker()) {
            tracker.init();
            UserAction[] actions = {
                    new CreateAction()
            };
            new StartUI().init(validate, tracker, Arrays.asList(actions));
        } catch (Exception e) {
            e.printStackTrace();
        }
       /*// List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction());
        actions.add(new ShowAllAction());
        actions.add(new EditItemAction());
        actions.add(new DeleteItemAction());
        actions.add(new FindByIdItemAction());
        actions.add(new FindByNameItemsAction());
*/
        //new StartUI().init(validate, memTracker, actions);
    }
}
