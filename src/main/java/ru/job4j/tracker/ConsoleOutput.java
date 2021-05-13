package ru.job4j.tracker;

public class ConsoleOutput implements Output {

    private final StringBuilder buffer = new StringBuilder();

    @Override
    public void println(Object obj) {
        if (obj != null) {
            buffer.append(obj.toString());
        } else {
            buffer.append("null");
        }
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
