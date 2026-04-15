package org.vadim.database;

public record Item(
        Integer userId,
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
                item.listName.equals(this.listName) &&
                item.product.equals(this.product);
    }
}
