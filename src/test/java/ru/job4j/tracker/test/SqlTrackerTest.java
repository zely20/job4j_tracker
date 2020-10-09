package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.ConnectionRollback;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.SqlTracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SqlTrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void createItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("desc").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("1", "pen");
            Item item2 = new Item("2", "pen");
            tracker.add(item1);
            tracker.add(item2);
            if(!tracker.delete(item2.getId())){
                System.out.println("false");
            };
            assertThat(tracker.findByName("pen").size(), is(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void replaceItem() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            Item item1 = new Item("1", "pen");
            Item item2 = new Item("2", "book");
            assertTrue(tracker.replace(item1.getId(), item2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
