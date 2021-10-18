package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "items", foreignKeys = {@ForeignKey(entity = ShoppingList.class, parentColumns = {"id"}, childColumns = "list_id")})
public class ShoppingItem {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "list_id")
    public int shoppingListId;

    public String name;
    public int amount = 1;
    public boolean checked = false;

    @Ignore
    public ShoppingItem(String name, int shoppingListId) {
        this.name = name;
        this.shoppingListId = shoppingListId;
    }

    public ShoppingItem(String name, int amount, int shoppingListId) {
        this(name, shoppingListId);
        this.amount = amount;
    }


    public boolean isChecked() {
        return checked;
    }

    public void toggleCheck() {
        this.checked = !checked;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
