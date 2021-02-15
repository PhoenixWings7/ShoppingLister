package com.phoenixwings7.shoppinglisterassignment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ItemPlaceholderAdapter extends RecyclerView.Adapter<ItemPlaceholderView>{
    private List<ShoppingItem> itemsList;
    private LayoutInflater layoutInflater;
    private DetailsItemViewAdapterContract.DetailsActivity detailsActivity;

    public ItemPlaceholderAdapter(Context context, DetailsItemViewAdapterContract.DetailsActivity detailsActivity) {
        layoutInflater = LayoutInflater.from(context);
        this.detailsActivity = detailsActivity;
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
        holder.checkBox.setChecked(item.checked);

        holder.itemTitle.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                String newName = holder.itemTitle.getText().toString();
                if(!newName.equals(itemName)) {
                    detailsActivity.onItemNameChanged(newName, item.id);
                }
            }
        });

        holder.itemAmount.setOnFocusChangeListener(((view, hasFocus) -> {
            if (!hasFocus) {
                int newAmount;
                try {
                    newAmount = parseInt(holder.itemAmount.getText().toString());
                }
                catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
                if (newAmount != item.amount) {
                    detailsActivity.onItemAmountChanged(newAmount, item.id);
                }
            }
        }));

        holder.checkBox.setOnClickListener((view) -> {
            CheckBox checkBoxView = (CheckBox) view;
            boolean isChecked = checkBoxView.isChecked();
            detailsActivity.onCheckboxStateChanged(isChecked, item.id);
        });
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
