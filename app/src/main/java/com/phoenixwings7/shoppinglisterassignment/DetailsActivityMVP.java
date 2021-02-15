package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import java.util.List;

public interface DetailsActivityMVP {
    interface Presenter {
        void setUpDAO(Context appContext);
        void showListItemsInGUI(int listID);
        void addShoppingItem(String name, int shoppingListId);
        void addShoppingItem(String name, int amount, int shoppingListId);
        void changeItemName(String newName, int itemId);
        void changeItemAmount(int newAmount, int itemId);
        void changeItemCheckedState(boolean isChecked, int itemId);
    }

    interface View {
        void showItems(List<ShoppingItem> items);
        LifecycleOwner getViewLifecycleOwner();
    }
}
