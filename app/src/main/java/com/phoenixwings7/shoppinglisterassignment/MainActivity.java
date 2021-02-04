package com.phoenixwings7.shoppinglisterassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    public static final int[] TAB_TITLES_IDS = {R.string.tab_1, R.string.tab_2};
    private ViewPager2 viewPager2;
    private PlaceholderPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.view_pager);

        pagerAdapter = new PlaceholderPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

        // Set tabs with titles
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2,
                ((tab, position) -> tab.setText(TAB_TITLES_IDS[position])));
        tabLayoutMediator.attach();


    }
}