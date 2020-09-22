package ru.job4j.tracker;

public interface UserAction {

    public String name();

    public boolean execute(Input input, Store memTracker);
}
