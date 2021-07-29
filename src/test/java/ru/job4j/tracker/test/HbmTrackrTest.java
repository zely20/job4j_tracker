package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.HbmTracker;
import ru.job4j.tracker.Item;

import java.util.List;

import static org.junit.Assert.*;

public class HbmTrackrTest {

    @Test
    public void whenCreate() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Milk");
        hbmTracker.add(item);
        List<Item> all = hbmTracker.findAll();
        assertEquals(item, all.get(0));
    }

    @Test
    public void findAll() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Milk");
        Item item2 = new Item("Meet");
        hbmTracker.add(item);
        hbmTracker.add(item2);
        List<Item> all = hbmTracker.findAll();
        assertEquals(2, all.size());
    }

    @Test
    public void delete() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Milk");
        Item item2 = new Item("Meet");
        hbmTracker.add(item);
        hbmTracker.add(item2);
        hbmTracker.delete(1);
        List<Item> all = hbmTracker.findAll();
        assertEquals(1, all.size());
    }

    @Test
    public void findByName() {
        HbmTracker hbmTracker = new HbmTracker();
        Item item = new Item("Milk");
        Item item2 = new Item("Meet");
        hbmTracker.add(item);
        hbmTracker.add(item2);
        Item result = hbmTracker.findByName("Milk").get(0);
        assertEquals(item.getName(), result.getName());
    }
}
