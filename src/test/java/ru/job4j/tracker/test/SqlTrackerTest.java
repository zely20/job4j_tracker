package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class SqlTrackerTest {

    @Test
    public void createItem() {
        SqlTracker tracker = new SqlTracker();
        tracker.add(new Item("name", "desc"));
        assertThat(tracker.findByName("name").size(), is(1));
    }
}
