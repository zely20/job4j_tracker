package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

public class Tracker {
    /**
     * Массив для хранения заявок.
     */
    private final Item[] items = new Item[100];
    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод добавления заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(generateId());
        items[position++] = item;
        return item;
    }
    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
    /**
     * Метод возвращает все элементы массива без пустых(null) ячеек
     * @return массив Item
     */
    public Item[] findAll() {
        Item[] itemsWithoutNull = new Item[position];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                itemsWithoutNull[size] = items[i];
                size++;
            }
        }
        itemsWithoutNull = Arrays.copyOf(itemsWithoutNull,size);
        return itemsWithoutNull;
    }
    /**
     * Метод возвращает массив Item c элементоми в которых поле name равное принимаемому параметру String key
     * @return массив Item
     */
    public Item[] findByName(String key) {
        Item[] itemsByName = new Item[position];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if(items[i] != null && items[i].getName().equals(key)) {
                itemsByName[size] = items[i];
                size++;
             }
        }
        itemsByName = Arrays.copyOf(itemsByName, size);
        return itemsByName;
    }

    public Item findById(String key) {

        for (int i = 0; i < items.length; i++) {
            if(items[i] != null && items[i].getId().equals(key)) {
                return items[i];
            }
        }
        return null;
    }
}
