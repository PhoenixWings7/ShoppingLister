package com.phoenixwings7.shoppinglisterassignment.database;

import android.os.AsyncTask;

import androidx.annotation.NonNull;

public class InsertItemsAsyncTask extends AsyncTask<ShoppingItem, Void, Void> {
    @NonNull
    private final ShoppingItemsDao shoppingItemsDao;

    public InsertItemsAsyncTask(@NonNull ShoppingItemsDao shoppingItemsDao) {
        this.shoppingItemsDao = shoppingItemsDao;
    }


    @Override
    protected Void doInBackground(ShoppingItem... shoppingItems) {
        for (ShoppingItem item:shoppingItems) {
            shoppingItemsDao.addItem(item);
        }
        return null;
    }
}
