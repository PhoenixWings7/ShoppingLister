package com.phoenixwings7.shoppinglisterassignment.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;


public class ItemPlaceholderView extends RecyclerView.ViewHolder {
    EditText itemTitle;
    EditText itemAmount;
    CheckBox checkBox;

    public ItemPlaceholderView(@NonNull View itemView) {
        super(itemView);
        this.itemTitle = itemView.findViewById(R.id.item_title);
        this.itemAmount = itemView.findViewById(R.id.item_amount);
        this.checkBox = itemView.findViewById(R.id.item_checkbox);
    }


}
