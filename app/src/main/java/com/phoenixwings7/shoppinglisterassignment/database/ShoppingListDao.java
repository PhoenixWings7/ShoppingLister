package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists WHERE NOT is_archived ORDER BY id ASC")
    LiveData<List<ShoppingList>> getActiveShoppingLists();

    @Query("SELECT * FROM shopping_lists WHERE is_archived = 1 ORDER BY id ASC")
    LiveData<List<ShoppingList>> getArchivedShoppingLists();


    @Insert(entity = ShoppingList.class)
    void saveShoppingList(ShoppingList shoppingList);
}
