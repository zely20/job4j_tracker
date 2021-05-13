package ru.job4j.tracker.test;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Ignore
public class FindByNameItemsActionTest {

    @Test
    public void whenCheckOutput() {
        Output output = new ConsoleOutput();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Store memTracker = new SqlTracker();
        Item item = new Item("fix bug");
        memTracker.add(item);
        FindByNameItemsAction act = new FindByNameItemsAction(output);
        act.execute(new StubInput(new String[] {"fix bug"}), memTracker);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(item.toString())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
