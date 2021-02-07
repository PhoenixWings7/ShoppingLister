package com.phoenixwings7.shoppinglisterassignment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

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

        // Set a callback to the presenter to set content of the placeholder
        // based on a selected tab index (position)
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mainPresenter.getPlaceholderFragmentsContentFromDB(position);
            }
        });

    }

    @Override
    public void setPresenter(MainMVP.Presenter presenter) {
        this.mainPresenter = presenter;
    }

    @Override
    public MainMVP.Presenter getMainPresenter() {
        return this.mainPresenter;
    }

    @Override
    public void onDestroyMainActivity() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void startNewListActivity() {

    }

    @Override
    public void showListsInGUI(List<ShoppingList> shoppingLists) {

    }
}