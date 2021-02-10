package com.phoenixwings7.shoppinglisterassignment.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.R;

public class ListPlaceholderView extends RecyclerView.ViewHolder {
    final TextView title_view;
    final TextView subtitle_view;

    public ListPlaceholderView(@NonNull View itemView) {
        super(itemView);
        this.title_view = itemView.findViewById(R.id.title_holder);
        this.subtitle_view = itemView.findViewById(R.id.subtitle_holder);
    }


}
