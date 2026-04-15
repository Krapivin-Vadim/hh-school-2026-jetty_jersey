package org.vadim.database;

import java.util.LinkedList;
import java.util.List;

public class DataBase {
    private static final List<Item> db = new LinkedList<>();

    public static Item addItem(Item item) {
        db.add(item);
        return item;
    }

    public static Item removeItem(Item item) {
        db.remove(item);
        return item;
    }

    public static ShopList getProducts(Integer userId, String listName) {
        return new ShopList(
                userId,
                listName,
                db.stream()
                        .filter(item -> item.listName().equals(listName) && item.userId().equals(userId))
                        .map(Item::product)
                        .toList()
        );
    }

}
