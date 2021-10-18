package com.phoenixwings7.shoppinglisterassignment

import androidx.lifecycle.LiveData
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem

interface ListItemsRepo {
    fun getShoppingListItems(listID: Int): LiveData<List<ShoppingItem>>?

    fun addItem(item: ShoppingItem)

    fun updateItem(item: ShoppingItem)

}