package com.phoenixwings7.shoppinglisterassignment.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;


public class ItemPlaceholderView extends RecyclerView.ViewHolder {
    TextView itemTitle;
    TextView itemAmount;

    public ItemPlaceholderView(@NonNull View itemView) {
        super(itemView);
        this.itemTitle = itemView.findViewById(R.id.item_title);
        this.itemAmount = itemView.findViewById(R.id.item_amount);
    }


}
