package com.phoenixwings7.shoppinglisterassignment

import androidx.lifecycle.LiveData
import com.phoenixwings7.shoppinglisterassignment.database.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AppRepository(appDatabase: AppDatabase) : ListRepo, ListItemsRepo {
    private val listDao: ShoppingListDao = appDatabase.shoppingListDao()
    private val itemsDao: ShoppingItemsDao = appDatabase.shoppingItemsDao()

    override fun getActiveShoppingLists(): LiveData<List<ShoppingList?>?>? {
        return listDao.activeShoppingLists
    }

    override fun getArchivedShoppingLists(): LiveData<List<ShoppingList?>?>? {
        return listDao.archivedShoppingLists
    }

    override fun getShoppingListTitle(id: Int): String? {
        return listDao.getShoppingListTitle(id)
    }

    override fun saveShoppingList(shoppingList: ShoppingList?) {
        val completable = listDao.saveShoppingList(shoppingList)
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun getShoppingListItems(listID: Int): LiveData<List<ShoppingItem>> {
        return itemsDao.getShoppingListItems(listID)
    }

    override fun addItem(item: ShoppingItem) {
        val completable = itemsDao.addItem(item)
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun updateItem(item: ShoppingItem) {
        val completable = itemsDao.updateItem(item)
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}