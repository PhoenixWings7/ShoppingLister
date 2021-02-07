package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.AppDatabase;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingListDao;

import java.util.List;

public class MainActivityPresenter implements MainMVP.Presenter {
    @Nullable private MainMVP.View mainView;
    private ShoppingListDao shoppingListDao;

    MainActivityPresenter(MainMVP.View mainView, Context appContext) {
        this.mainView = mainView;
        setUpDAO(appContext);
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
    public void saveShoppingList(ShoppingList newShoppingList) {
        shoppingListDao.saveShoppingList(newShoppingList);
    }

    @Override
    public LiveData<List<ShoppingList>> getPlaceholderFragmentsContentFromDB(int tabPosition) {

        switch (tabPosition) {
            case 1:
                return shoppingListDao.getArchivedShoppingLists();
            default:
                return shoppingListDao.getActiveShoppingLists();
        }
    }

}
