package com.phoenixwings7.shoppinglisterassignment;

public class ShoppingItem {
    private String name;
    private int amount = 1;
    private boolean checked = false;

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
