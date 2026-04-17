package org.vadim.database;

public record Item(
        // userId, itemId - имитация составного ключа
        Integer userId,
        Integer itemId,
        String listName,
        String product
) {
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(Item.class)){
            return false;
        }
        Item item = (Item) obj;
        return item.userId.equals(this.userId) &&
                item.itemId.equals(this.itemId);
    }
}
