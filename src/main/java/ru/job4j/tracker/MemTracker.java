package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker implements Store {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();
    private int id = 0;
    @Override
    public void init() {

    }

    /**
     * Метод добавления заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items.add(item);
        return item;
    }

    @Override
    public boolean replace(Integer id, Item item) {
        return false;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private Integer generateId() {
        return ++id;
    }

    /**
     * Метод возвращает все элементы массива без пустых(null) ячеек
     *
     * @return массив Item
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Метод возвращает массив Item c элементоми в которых поле name равное принимаемому параметру String key
     *
     * @return массив Item
     */
    public List<Item> findByName(String key) {
        List<Item> itemsByName = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                itemsByName.add(item);
            }
        }
        return itemsByName;
    }

    public Item findById(Integer id) {
        int i = indexOf(id);
        if (i >= 0) {
            return items.get(i);
        }
        return null;
    }

    public boolean replace(Item item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.set(index, item);
            return true;
        }
        return false;
    }

    public boolean delete(Integer id) {
        int i = indexOf(id);
        if (i >= 0) {
            items.remove(i);
            return true;
        }
        return false;
    }

    public int indexOf(Integer id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void close() throws Exception {

    }
}
