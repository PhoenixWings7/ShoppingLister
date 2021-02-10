package com.phoenixwings7.shoppinglisterassignment.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "shopping_lists",
        indices = {@Index(value = {"title", "is_archived"})})

public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "title")
    public String title = "Shopping List";

    @ColumnInfo(name = "is_archived")
    public boolean isArchived = false;

    @Ignore
    private final int groceries_done = 0;
    @Ignore
    private final ArrayList<ShoppingItem> groceries = new ArrayList<>();


    public ShoppingList(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void archive(){
        this.isArchived = true;
    }

    public boolean isArchived() {
        return isArchived;
    }

}
