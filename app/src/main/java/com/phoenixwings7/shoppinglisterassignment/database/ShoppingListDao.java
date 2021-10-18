package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists WHERE NOT is_archived ORDER BY id ASC")
    LiveData<List<ShoppingList>> getActiveShoppingLists();

    @Query("SELECT * FROM shopping_lists WHERE is_archived = 1 ORDER BY id ASC")
    LiveData<List<ShoppingList>> getArchivedShoppingLists();

    @Query("SELECT title FROM shopping_lists WHERE id = :id")
    String getShoppingListTitle(int id);

    @Insert(entity = ShoppingList.class)
    Completable saveShoppingList(ShoppingList shoppingList);
}
