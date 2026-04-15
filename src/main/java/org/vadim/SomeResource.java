package org.vadim;

import jakarta.ws.rs.*;
import org.vadim.database.DataBase;
import org.vadim.database.Item;
import org.vadim.database.ShopList;

import jakarta.ws.rs.core.MediaType;

@Path("/some_resource")
@Produces(MediaType.APPLICATION_JSON)
public class SomeResource {

    @GET
    @Path("/{userId}_{listName}")
    public ShopList readList(@PathParam("userId") Integer userId, @PathParam("listName") String listName) {
        return DataBase.getProducts(userId, listName);
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Item addItem(Item item) {
        try {
            return DataBase.addItem(item);
        } catch (RuntimeException e) {
            return new Item(0, "0", "0");
        }

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Item removeItem(Item item) {
        return DataBase.removeItem(item);
    }

}
