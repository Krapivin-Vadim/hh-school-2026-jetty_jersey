package org.vadim;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.vadim.database.DataBase;
import org.vadim.database.Item;
import org.vadim.database.ShopList;

import jakarta.ws.rs.core.MediaType;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @GET
    @Path("/{userId}/{listName}")
    public ShopList readList(@PathParam("userId") Integer userId, @PathParam("listName") String listName) {
        return DataBase.getProducts(userId, listName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(Item item) {
        if (item == null){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        var added = DataBase.addItem(item);
        return Response.ok(added).build();
    }

    @DELETE
    @Path("/{userId}/{itemId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeItem(@PathParam("userId") Integer userId, @PathParam("itemId") Integer itemId) {
        Item deleted = DataBase.removeItemByIDs(userId, itemId);
        if (deleted == null){
            return Response.notModified().build();
        }
        return Response.ok(deleted).build();
    }

}