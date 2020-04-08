package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.ValidateInput;
import ru.job4j.tracker.ValidateStubInput;

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
        String[] data = {"one", "1"};
        ValidateInput input = new ValidateStubInput(data);
        input.askInt("Enter");
        String expected = "Please enter validate data again.";
        assertThat(
                mem,
                is(expected));
        System.setOut(out);
    }
}
