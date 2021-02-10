package com.phoenixwings7.shoppinglisterassignment.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.phoenixwings7.shoppinglisterassignment.MainActivityPresenter;
import com.phoenixwings7.shoppinglisterassignment.MainMVP;
import com.phoenixwings7.shoppinglisterassignment.R;

public class MainActivity extends AppCompatActivity implements MainMVP.View {
    public static final int[] TAB_TITLES_IDS = {R.string.tab_1, R.string.tab_2};
    private ViewPager2 viewPager2;
    private PlaceholderPagerAdapter pagerAdapter;
    MainMVP.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.view_pager);

        // Set presenter
        mainPresenter = new MainActivityPresenter(this, getApplicationContext());

        pagerAdapter = new PlaceholderPagerAdapter(this, mainPresenter);
        viewPager2.setAdapter(pagerAdapter);

        // Set tabs with titles
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,
                ((tab, position) -> tab.setText(TAB_TITLES_IDS[position])));
        tabLayoutMediator.attach();

        // Set fab callback
        FloatingActionButton fab = findViewById(R.id.new_list_fab);
        fab.setOnClickListener(view -> mainPresenter.newListFabClicked());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroyView();
    }

    @Override
    public void startNewListActivity() {
        Intent intent = new Intent(this, NewListActivity.class);
        startActivityForResult(intent, NewListActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NewListActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            String listTitle = data.getStringExtra(NewListActivity.RESULT_TITLE);
            mainPresenter.saveShoppingList(listTitle);
        }

    }
}