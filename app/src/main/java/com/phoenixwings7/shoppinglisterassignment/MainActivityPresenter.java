package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.AppDatabase;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

public class MainActivityPresenter implements MainMVP.Presenter {
    @Nullable private MainMVP.View mainView;
    private AppRepository appRepository;
    private LiveData<List<ShoppingList>> activeShoppingLists;
    private LiveData<List<ShoppingList>> archivedShoppingLists;


    public MainActivityPresenter(MainMVP.View mainView, Context appContext) {
        this.mainView = mainView;
        setUpDAO(appContext);
        setUpLiveData();
    }

    private void setUpLiveData() {
        this.activeShoppingLists = appRepository.getActiveShoppingLists();
        this.archivedShoppingLists = appRepository.getArchivedShoppingLists();
    }

    @Override
    public boolean setUpDAO(Context appContext) {
        if (mainView != null) {
            appRepository = new AppRepository(AppDatabase.getInstance(appContext));
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        mainView = null;
    }

    @Override
    public void saveShoppingList(String title) {
        appRepository.saveShoppingList(new ShoppingList(title), () -> {}, x -> {});
    }

    @Override
    public LiveData<List<ShoppingList>> getPlaceholderFragmentsContent(int tabPosition) {
        switch (tabPosition) {
            case 1:
                return archivedShoppingLists;
            default:
                return activeShoppingLists;
        }
    }

    @Override
    public void newListFabClicked() {
        assert mainView != null;
        mainView.startNewListActivity();
    }

    @Override
    public void showListDetails(int clickedListId) {
        String title = getShoppingListTitleById(clickedListId);
        mainView.startListDetailsActivity(clickedListId, title);
    }

    private String getShoppingListTitleById(int listId) {
        for (ShoppingList shoppingList: activeShoppingLists.getValue()) {
            if (shoppingList.id == listId) {
                return shoppingList.title;
            }
        }
        return null;
    }
}
