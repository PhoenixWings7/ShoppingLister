package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import java.util.List;

public interface DetailsActivityMVP {
    interface Presenter {
        void setUpDAO(Context appContext);
        void showListItemsInGUI(int listID);
        void addShoppingItem(String name);
        void addShoppingItem(String name, int amount);
    }

    interface View {
        void showItems(List<ShoppingItem> items);
        LifecycleOwner getViewLifecycleOwner();
    }
}
