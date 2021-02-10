package com.phoenixwings7.shoppinglisterassignment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import java.util.List;

public class ItemPlaceholderAdapter extends RecyclerView.Adapter<ItemPlaceholderView>{
    private List<ShoppingItem> itemsList;
    private LayoutInflater layoutInflater;

    public ItemPlaceholderAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemPlaceholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_single_item_view, parent, false);
        return new ItemPlaceholderView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPlaceholderView holder, int position) {
        ShoppingItem item = itemsList.get(position);
        String itemName = item.name;
        String itemAmount = String.valueOf(item.amount);

        holder.itemTitle.setText(itemName);
        holder.itemAmount.setText(itemAmount);
    }

    @Override
    public int getItemCount() {
        if (itemsList != null) {
            return itemsList.size();
        }
        return 0;
    }

    public void setData(List<ShoppingItem> data) {
        this.itemsList = data;
        notifyDataSetChanged();
    }
}
