package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

public interface MainMVP {

    interface View {
        void startNewListActivity();
    }

    interface Presenter {
        boolean setUpDAO(Context appContext);
        void onDestroyView();
        void saveShoppingList(String title);
        LiveData<List<ShoppingList>> getPlaceholderFragmentsContent(int tabPosition);
        void newListFabClicked();
    }

    interface PlaceholderFragment {
        void setDataObserver(LiveData<List<ShoppingList>> liveData);
    }
}
