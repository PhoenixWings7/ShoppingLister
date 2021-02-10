package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.AppDatabase;
import com.phoenixwings7.shoppinglisterassignment.database.InsertItemsAsyncTask;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItemsDao;
import com.phoenixwings7.shoppinglisterassignment.database.UpdateItemAsyncTask;

import java.util.List;

public class DetailsActivityPresenter implements DetailsActivityMVP.Presenter {
    private DetailsActivityMVP.View detailsView;
    private ShoppingItemsDao shoppingItemsDao;
    private LiveData<List<ShoppingItem>> itemsListLiveData;

    public DetailsActivityPresenter(Context appContext, DetailsActivityMVP.View detailsView, int listID){
        this.detailsView = detailsView;
        setUpDAO(appContext);
        setUpLiveData(listID);
    };

    @Override
    public void setUpDAO(Context appContext) {
        this.shoppingItemsDao = AppDatabase.getInstance(appContext).shoppingItemsDao();
    }

    @Override
    public void showListItemsInGUI(int listID) {
        detailsView.showItems(getShoppingListItems());
    }

    @Override
    public void addShoppingItem(String name) {
        addShoppingItem(name, 0);
    }

    @Override
    public void addShoppingItem(String name, int amount) {
        if (name != null) {
            ShoppingItem shoppingItem;
            if (amount > 0) {
                shoppingItem = new ShoppingItem(name, amount);
            } else {
                shoppingItem = new ShoppingItem(name);
            }
            InsertItemsAsyncTask insertTask = new InsertItemsAsyncTask(shoppingItemsDao);
            insertTask.execute(shoppingItem);
        }
    }

    @Override
    public void changeItemName(String newName, int itemId) {
        ShoppingItem item = getItemById(itemId);
        if (item != null) {
            item.name = newName;
            updateItemInDB(item);
        }
    }

    @Override
    public void changeItemAmount(int newAmount, int itemId) {
        ShoppingItem item = getItemById(itemId);
        if (item != null) {
            item.amount = newAmount;
            updateItemInDB(item);
        }
    }

    @Override
    public void changeItemCheckedState(boolean isChecked, int itemId) {
        ShoppingItem item = getItemById(itemId);
        item.checked = isChecked;
        updateItemInDB(item);
    }

    private void setUpLiveData(int listID) {
        itemsListLiveData = shoppingItemsDao.getShoppingListItems(listID);
        itemsListLiveData.observe(detailsView.getViewLifecycleOwner(),
                data -> detailsView.showItems(data));
    }

    private List<ShoppingItem> getShoppingListItems(){
        return itemsListLiveData.getValue();
    }

    private ShoppingItem getItemById(int itemId) {
        List<ShoppingItem> shoppingItems = itemsListLiveData.getValue();
        for (ShoppingItem item:shoppingItems) {
            if (item.id == itemId) {
                return item;
            }
        }
        return null;
    }

    private void updateItemInDB(ShoppingItem shoppingItem) {
        UpdateItemAsyncTask asyncTask = new UpdateItemAsyncTask(shoppingItemsDao);
        asyncTask.execute(shoppingItem);
    }
}
