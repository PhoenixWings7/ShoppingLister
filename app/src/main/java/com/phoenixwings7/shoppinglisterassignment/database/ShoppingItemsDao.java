package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ShoppingItemsDao {
    @Query("SELECT * FROM items WHERE list_id = :listID")
    LiveData<List<ShoppingItem>> getShoppingListItems(int listID);

    @Insert(entity = ShoppingItem.class)
    Completable addItem(ShoppingItem item);

    @Update(entity = ShoppingItem.class)
    Completable updateItem(ShoppingItem item);
}
