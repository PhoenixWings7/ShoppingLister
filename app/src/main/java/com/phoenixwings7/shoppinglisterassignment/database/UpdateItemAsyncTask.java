package com.phoenixwings7.shoppinglisterassignment.database;

import android.os.AsyncTask;

public class UpdateItemAsyncTask extends AsyncTask<ShoppingItem, Void, Void> {

    private final ShoppingItemsDao itemsDao;

    public UpdateItemAsyncTask(ShoppingItemsDao itemsDao){
        this.itemsDao = itemsDao;
    }
    @Override
    protected Void doInBackground(ShoppingItem... shoppingItems) {
        ShoppingItem shoppingItem = shoppingItems[0];
        itemsDao.updateItem(shoppingItem);
        return null;
    }
}
