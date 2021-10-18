package com.phoenixwings7.shoppinglisterassignment

import androidx.lifecycle.LiveData
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer

interface ListRepo {
    fun getActiveShoppingLists(): LiveData<List<ShoppingList?>?>?

    fun getArchivedShoppingLists(): LiveData<List<ShoppingList?>?>?

    fun getShoppingListTitle(id: Int): String?

    fun saveShoppingList(shoppingList: ShoppingList?, actionOnComplete: Action,
                         onErrorAction: Consumer<Throwable>)
}
