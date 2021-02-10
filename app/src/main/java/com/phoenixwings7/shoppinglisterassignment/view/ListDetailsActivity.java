package com.phoenixwings7.shoppinglisterassignment.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.phoenixwings7.shoppinglisterassignment.DetailsActivityMVP;
import com.phoenixwings7.shoppinglisterassignment.DetailsActivityPresenter;
import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingItem;

import org.w3c.dom.Text;

import java.util.List;

public class ListDetailsActivity  extends AppCompatActivity implements DetailsActivityMVP.View {

    public static final String EXTRA_LIST_TITLE = "list_title";
    public static final String EXTRA_LIST_ID = "list_id";
    private ItemPlaceholderAdapter itemPlaceholderAdapter;
    private DetailsActivityMVP.Presenter presenter;
    private int listID;
    private String listTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        if (savedInstanceState != null) {
            listID = savedInstanceState.getInt(EXTRA_LIST_ID);
            listTitle = savedInstanceState.getString(EXTRA_LIST_TITLE);
        }

        // set list's title view
        TextView title = findViewById(R.id.detail_view_list_title);
        title.setText(listTitle);

        // set presenter
        this.presenter = new DetailsActivityPresenter(getApplicationContext(), this, listID);

        // set recyclerview for items
        RecyclerView itemListRecyclerView = findViewById(R.id.details_recyclerview);
        itemPlaceholderAdapter = new ItemPlaceholderAdapter();
        itemListRecyclerView.setAdapter(itemPlaceholderAdapter);
        itemListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set response for fab
        FloatingActionButton fab = findViewById(R.id.add_item_fab);
        setFabResponse(fab);

        // set response for saving button
        Button saveBtn = findViewById(R.id.save_details_btn);
        saveBtn.setOnClickListener(view -> finish());
    }

    private void setFabResponse(FloatingActionButton fab) {
        fab.setOnClickListener(view -> {
            presenter.addShoppingItem(getString(R.string.default_item_name));
        });
    }

    @Override
    public void showItems(List<ShoppingItem> items) {
        itemPlaceholderAdapter.setData(items);
    }

    @Override
    public LifecycleOwner getViewLifecycleOwner() {
        return this;
    }
}