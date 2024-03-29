package ru.job4j.tracker;

import ru.job4j.tracker.react.Observe;

import java.util.List;

public interface Store extends AutoCloseable {
    void init();
    Item add(Item item);
    boolean replace(Integer id, Item item);
    boolean delete(Integer id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(Integer id);
    List<Item> findReactAll(Observe<Item> observe);
}
