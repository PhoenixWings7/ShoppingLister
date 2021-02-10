package com.phoenixwings7.shoppinglisterassignment.view;

public interface DetailsItemViewAdapterContract {
    interface DetailsActivity {
        void onItemNameChanged(String newName, int itemId);
        void onItemAmountChanged(int amount, int itemId);
    }
    interface RecyclerViewAdapter {}
}
