package com.phoenixwings7.shoppinglisterassignment

import androidx.lifecycle.LiveData
import com.phoenixwings7.shoppinglisterassignment.database.AppDatabase
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingListDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class AppRepository(appDatabase: AppDatabase) : ListRepo {
    private val listDao: ShoppingListDao = appDatabase.shoppingListDao()

    override fun getActiveShoppingLists(): LiveData<List<ShoppingList?>?>? {
        return listDao.activeShoppingLists
    }

    override fun getArchivedShoppingLists(): LiveData<List<ShoppingList?>?>? {
        return listDao.archivedShoppingLists
    }

    override fun getShoppingListTitle(id: Int): String? {
        return listDao.getShoppingListTitle(id)
    }

    override fun saveShoppingList(shoppingList: ShoppingList?, actionOnComplete: Action,
                                  onErrorAction: Consumer<Throwable>) {
        val completable = listDao.saveShoppingList(shoppingList)
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(actionOnComplete, onErrorAction)
    }

}