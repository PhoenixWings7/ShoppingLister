package com.phoenixwings7.shoppinglisterassignment;

import java.util.ArrayList;

public class ShoppingList {
    private String title = "Shopping List";
    private int groceries_done = 0;
    private ArrayList<ShoppingItem> groceries = new ArrayList<>();
    private boolean isArchived = false;


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
