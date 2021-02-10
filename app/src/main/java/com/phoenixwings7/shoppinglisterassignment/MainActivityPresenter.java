package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.AppDatabase;
import com.phoenixwings7.shoppinglisterassignment.database.InsertListAsyncTask;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingListDao;

import java.util.List;

public class MainActivityPresenter implements MainMVP.Presenter {
    @Nullable private MainMVP.View mainView;
    private ShoppingListDao shoppingListDao;
    private LiveData<List<ShoppingList>> activeShoppingLists;
    private LiveData<List<ShoppingList>> archivedShoppingLists;


    public MainActivityPresenter(MainMVP.View mainView, Context appContext) {
        this.mainView = mainView;
        setUpDAO(appContext);
        setUpLiveData();
    }

    private void setUpLiveData() {
        this.activeShoppingLists = shoppingListDao.getActiveShoppingLists();
        this.archivedShoppingLists = shoppingListDao.getArchivedShoppingLists();
    }

    @Override
    public boolean setUpDAO(Context appContext) {
        if (mainView != null) {
            shoppingListDao = AppDatabase.getInstance(appContext).shoppingListDao();
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
        InsertListAsyncTask insertListAsyncTask = new InsertListAsyncTask(shoppingListDao);
        insertListAsyncTask.execute(new ShoppingList(title));
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
}
