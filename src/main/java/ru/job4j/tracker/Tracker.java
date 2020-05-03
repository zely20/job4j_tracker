package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final List<Item> items = new ArrayList<>();
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

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
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

    public Item findById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
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

    public boolean delete(String id) {
        for (Item item : items){
            if(item.getId().equals(id)) {
                int index = items.indexOf(item);
                items.remove(index);
                return true;
            }
        }
        return false;
    }
}
