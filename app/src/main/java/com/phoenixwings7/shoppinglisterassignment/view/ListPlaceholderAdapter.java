package com.phoenixwings7.shoppinglisterassignment.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.MainMVP;
import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ListPlaceholderAdapter extends RecyclerView.Adapter<ListPlaceholderView>{
    private List<ShoppingList> shoppingLists = new ArrayList<>();
    private final LayoutInflater layoutInflater;
    MainMVP.Presenter mainPresenter;

    ListPlaceholderAdapter(Context context, MainMVP.Presenter mainPresenter) {
        layoutInflater = LayoutInflater.from(context);
        this.mainPresenter = mainPresenter;
    }


    @NonNull
    @Override
    public ListPlaceholderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listItemView = layoutInflater.inflate(R.layout.recyclerview_list_placeholder,
                parent, false);
        return new ListPlaceholderView(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPlaceholderView holder, int position) {
        holder.title_view.setText(shoppingLists.get(position).getTitle());
        holder.position = position;
        holder.setOnClickListener(view -> {
            mainPresenter.showListDetails(shoppingLists.get(position).id);
        });
    }


    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public void setData(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
        this.notifyDataSetChanged();
    }
}
