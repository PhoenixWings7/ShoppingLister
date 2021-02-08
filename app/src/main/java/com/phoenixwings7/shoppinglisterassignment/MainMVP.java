package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

public interface MainMVP {

    interface View {
        void setPresenter(Presenter presenter);
        Presenter getMainPresenter();
        void onDestroyMainActivity();
        Context getContext();
        void startNewListActivity();
        void showListsInGUI(List<ShoppingList> shoppingLists);

    }

    interface Presenter {
        boolean setUpDAO(Context appContext);
        void onDestroyView();
        void saveShoppingList(ShoppingList newShoppingList);
        LiveData<List<ShoppingList>> getPlaceholderFragmentsContentFromDB(int tabPosition);
        void newListFabClicked();
    }

    interface PlaceholderFragment {
        void setFragmentContentObserver();
        void updateFragmentContent(List<ShoppingList> shoppingLists);
    }
}
