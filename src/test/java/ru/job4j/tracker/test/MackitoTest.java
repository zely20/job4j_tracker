package ru.job4j.tracker.test;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class MackitoTest {

    @Test
    public void editActionTest() {

        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        EditItemAction editItemAction = new EditItemAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        editItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item was not replace"));
        assertThat(memTracker.findAll().get(0).getName(), is("test"));
    }

    @Test
    public void deleteActionTest() {
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        DeleteItemAction deleteItemAction = new DeleteItemAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        deleteItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item deleted"));
    }

    @Test
    public void findByIdTest() {
        String ln = System.lineSeparator();
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        FindByIdItemAction findByIdItemAction = new FindByIdItemAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(item.getId());
        findByIdItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item foundItem id is " + "1, " + "Item name is test"));
    }

    @Test
    public void findByNameTest() {
        String ln = System.lineSeparator();
        Output out = new ConsoleOutput();
        Store memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test"));
        FindByNameItemsAction findByIdItemAction = new FindByNameItemsAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        findByIdItemAction.execute(input, memTracker);
        assertThat(out.toString(), is("Item id is 1, Item name is test"));
    }
}
