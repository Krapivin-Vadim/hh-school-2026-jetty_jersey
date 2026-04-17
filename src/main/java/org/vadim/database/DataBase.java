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

    public static Item removeItemByIDs(Integer userId, Integer itemId){
        List<Item> to_delete = db.stream()
                .filter(item -> item.itemId().equals(itemId) && item.userId().equals(userId))
                .toList();

        if(to_delete.size() != 1){
            return null;
        }

        db.remove(to_delete.getFirst());
        return to_delete.getFirst();
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
