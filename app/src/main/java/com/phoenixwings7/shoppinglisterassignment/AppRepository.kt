package com.phoenixwings7.shoppinglisterassignment

import androidx.lifecycle.LiveData
import com.phoenixwings7.shoppinglisterassignment.database.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
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
        executeSimpleCompletable(completable)
    }

    override fun deleteShoppingList(shoppingList: ShoppingList?) {
        val completable = listDao.deleteList(shoppingList)
        executeSimpleCompletable(completable)
    }

    override fun deleteShoppingList(listId: Int) {
        val completable = listDao.deleteList(listId)
        executeSimpleCompletable(completable)
    }

    override fun getShoppingListItems(listID: Int): LiveData<List<ShoppingItem>> {
        return itemsDao.getShoppingListItems(listID)
    }

    override fun addItem(item: ShoppingItem) {
        val completable = itemsDao.addItem(item)
        executeSimpleCompletable(completable)
    }

    override fun updateItem(item: ShoppingItem) {
        val completable = itemsDao.updateItem(item)
        executeSimpleCompletable(completable)
    }

    override fun deleteItem(item: ShoppingItem?) {
        val completable = itemsDao.deleteItem(item)
        executeSimpleCompletable(completable)
    }

    override fun deleteItem(itemId: Int) {
        val completable = itemsDao.deleteItem(itemId)
        executeSimpleCompletable(completable)
    }

    private fun executeSimpleCompletable(completable: Completable) {
        completable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}