package ru.job4j.tracker.test;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@Ignore
public class MemTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        memTracker.add(item);
        Item result = memTracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void findAllTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        memTracker.add(item);
        memTracker.add(item2);
        expected.add(item);
        expected.add(item2);
        assertThat(memTracker.findAll(), is(expected));
    }

    @Test
    public void findByNameOneTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        memTracker.add(item);
        memTracker.add(item2);
        expected.add(item);
        assertThat(memTracker.findByName("test1"), is(expected));
    }

    @Test
    public void findByNameTwoTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        memTracker.add(item);
        memTracker.add(item2);
        memTracker.add(item3);
        List<Item> expected = new ArrayList<>();
        expected.add(item2);
        expected.add(item3);
        assertThat(memTracker.findByName("test2"), is(expected));
    }

    @Test
    public void findByNameNoTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        List<Item> expected = new ArrayList<>();
        memTracker.add(item);
        memTracker.add(item2);
        memTracker.add(item3);
        assertThat(memTracker.findByName("test5"), is(expected));
    }

    @Test
    public void findByIdTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        memTracker.add(item);
        memTracker.add(item2);
        memTracker.add(item3);
        assertThat(memTracker.findById(item.getId()), is(item));
    }

    @Test
    public void notFindByIdTest() {
        MemTracker memTracker = new MemTracker();
        Item item = new Item("test1");
        Item item2 = new Item("test2");
        Item item3 = new Item("test2");
        memTracker.add(item);
        memTracker.add(item2);
        memTracker.add(item3);
        Item item5 = null;
        assertThat(memTracker.findById(687428748), is(item5));
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
        MemTracker memTracker = new MemTracker();
        Item bug = new Item("Bug");
        Item bug1 = new Item("Bug1");
        memTracker.add(bug);
        memTracker.add(bug1);
        Integer id = bug.getId();
        memTracker.delete(id);
        assertThat(memTracker.findById(id), is(nullValue()));
    }
}
