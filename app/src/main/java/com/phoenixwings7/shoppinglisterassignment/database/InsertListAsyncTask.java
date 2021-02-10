package com.phoenixwings7.shoppinglisterassignment.database;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class InsertListAsyncTask extends AsyncTask<ShoppingList, Void, Void> {
    @NonNull private final ShoppingListDao shoppingListDao;

    public InsertListAsyncTask(@NonNull ShoppingListDao shoppingListDao) {
        this.shoppingListDao = shoppingListDao;
    }

    @Override
    protected Void doInBackground(ShoppingList... shoppingLists) {
        if (shoppingLists != null && shoppingLists.length > 0) {
            shoppingListDao.saveShoppingList(shoppingLists[0]);
        }
        return null;
    }

}
