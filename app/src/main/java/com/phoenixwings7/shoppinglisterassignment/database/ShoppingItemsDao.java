package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingItemsDao {
    @Query("SELECT * FROM items WHERE list_id = :listID")
    LiveData<List<ShoppingItem>> getShoppingListItems(int listID);

    @Insert(entity = ShoppingItem.class)
    void addItem(ShoppingItem item);
}
