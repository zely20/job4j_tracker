package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void findAllTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        tracker.add(item);
        tracker.add(item2);
        expected.add(item);
        expected.add(item2);
        assertThat(tracker.findAll(), is(expected));
    }

    @Test
    public void findByNameOneTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        tracker.add(item);
        tracker.add(item2);
        expected.add(item);
        assertThat(tracker.findByName("test1"), is(expected));
    }

    @Test
    public void findByNameTwoTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        assertThat(tracker.findByName("test2"), is(expected));
    }

    @Test
    public void findByNameNoTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("test5"), is(expected));
    }

    @Test
    public void findByIdTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void notFindByIdTest() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        Item item5 = null;
        assertThat(tracker.findById("68742874894"), is(item5));
    }

  /*  @Test
    public void whenReplace() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        tracker.add(bug);
        String id = bug.getId();
        Item bugWithDesc = new Item("Bug with description");
        tracker.replace(id, bugWithDesc);
        assertThat(tracker.findById(id).getName(), is("Bug with description"));
    }*/

    @Test
    public void whenDelete() {
        Tracker tracker = new Tracker();
        Item bug = new Item("Bug");
        Item bug1 = new Item("Bug1");
        tracker.add(bug);
        tracker.add(bug1);
        String id = bug.getId();
        tracker.delete(id);
        assertThat(tracker.findById(id), is(nullValue()));
    }
}
