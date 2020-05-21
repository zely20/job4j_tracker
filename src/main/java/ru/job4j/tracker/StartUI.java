package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {

    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            //this.showMenu(actions);
            actions.forEach(action -> System.out.println(action.name()));
            int select = input.askInt("Select: ",6);
            UserAction action = actions.get(select);
            run = action.execute(input, tracker);
        }
    }
/*
    private void showMenu(UserAction[] actions) {
        System.out.println("Menu.");
        for (int index = 0; index < actions.length; index++) {
            System.out.println(index + ". " + actions[index].name());
        }
    }*/

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = new Tracker();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction());
        actions.add(new ShowAllAction());
        actions.add(new EditItemAction());
        actions.add(new DeleteItemAction());
        actions.add(new FindByIdItemAction());
        actions.add(new FindByNameItemsAction());

        new StartUI().init(validate, tracker, actions);
    }
}
