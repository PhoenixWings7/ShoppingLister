package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

public interface MainMVP {

    interface View {
        void startNewListActivity();
        void startListDetailsActivity(int listId, String listTitle);
    }

    interface Presenter {
        boolean setUpDAO(Context appContext);
        void onDestroyView();
        void saveShoppingList(String title);
        LiveData<List<ShoppingList>> getPlaceholderFragmentsContent(int tabPosition);
        void newListFabClicked();
        void showListDetails(int clickedListId);
    }

    interface PlaceholderFragment {
        void setDataObserver(LiveData<List<ShoppingList>> liveData);
    }
}
