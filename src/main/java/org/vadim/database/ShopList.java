package org.vadim.database;

import java.util.List;

public record ShopList(
        Integer userId,
        String listName,
        List<String> products
) {
}
