package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id"}, tableName = "items")
public class ShoppingItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ForeignKey(entity = ShoppingList.class, parentColumns = {"list_id"}, childColumns = "id")
    @ColumnInfo(name = "list_id")
    public int shoppingListId;

    public String name;
    public int amount = 1;
    public boolean checked = false;

    public ShoppingItem(String name) {
        this.name = name;
    }

    ShoppingItem(String name, int amount) {
        this(name);
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
