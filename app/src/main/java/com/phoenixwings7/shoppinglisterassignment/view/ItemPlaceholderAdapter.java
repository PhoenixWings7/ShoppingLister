package com.phoenixwings7.shoppinglisterassignment.view;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import java.util.List;

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

        TextWatcher titleTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String newTitle = editable.toString();
                detailsActivity.onItemNameChanged(newTitle, item.id);
            }
        };
        holder.itemTitle.addTextChangedListener(titleTextWatcher);

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
        if (itemsList == null) {
            this.itemsList = data;
            notifyDataSetChanged();
        }
        else {
            updateData(data);
        }
    }

    public void updateData(List<ShoppingItem> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(getDiffCb(itemsList, data), false);
        this.itemsList = data;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    private DiffUtil.Callback getDiffCb(List<ShoppingItem> oldList, List<ShoppingItem> newList) {
        return new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                if (oldList.isEmpty()) {
                    return 0;
                }
                return oldList.size();
            }

            @Override
            public int getNewListSize() {
                if (newList.isEmpty()) {
                    return 0;
                }
                return newList.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                ShoppingItem oldItem = oldList.get(oldItemPosition);
                ShoppingItem newItem = newList.get(newItemPosition);
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                ShoppingItem oldItem = oldList.get(oldItemPosition);
                ShoppingItem newItem = newList.get(newItemPosition);
                boolean areContentsSame = false;

                if (oldItem.id == newItem.id) {
                    if (oldItem.name.equals(newItem.name)) {
                        if (oldItem.getAmount() == newItem.getAmount()) {
                            if (oldItem.isChecked() == newItem.isChecked()) {
                                areContentsSame = true;
                            }
                        }
                    }
                }

                return areContentsSame;
            }
        };
    }
}
