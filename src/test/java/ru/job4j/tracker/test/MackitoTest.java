package ru.job4j.tracker.test;

import org.junit.Test;
import ru.job4j.tracker.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MackitoTest {

    @Test
    public void editActionTest() {

        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        memTracker.add(new Item("test"));
        String replaceId = "0";
        EditItemAction editItemAction = new EditItemAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(replaceId);
        editItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item was not replace"));
        assertThat(memTracker.findAll().get(0).getName(), is("test"));
    }

    @Test
    public void deleteActionTest() {
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        memTracker.add(new Item("test"));
        String id = "1";
        DeleteItemAction deleteItemAction = new DeleteItemAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(id);
        deleteItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item deleted"));
    }

    @Test
    public void findByIdTest() {
        String ln = System.lineSeparator();
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        memTracker.add(new Item("test"));
        String id = "1";
        FindByIdItemAction findByIdItemAction = new FindByIdItemAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(id);
        findByIdItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item foundItem id is " + "1, " + "Item name is test"));
    }

    @Test
    public void findByNameTest() {
        String ln = System.lineSeparator();
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        memTracker.add(new Item("test"));
        String name = "test";
        FindByNameItemsAction findByIdItemAction = new FindByNameItemsAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(name);
        findByIdItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item id is 1, Item name is test"));
    }
}
