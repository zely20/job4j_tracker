package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"one", "1"})
        );
        input.askInt("Enter");
        assertThat(
                mem.toString(),
                is(String.format("Please enter validate data again.%n"))
        );
        System.setOut(out);
    }

    @Test
    public void whenInvalidNumberInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"6"})
        );
        input.askInt("Enter", 4);
        assertThat(
                mem.toString(),
                is(String.format("Please select key from menu.%n"))
        );
        System.setOut(out);
    }
}
