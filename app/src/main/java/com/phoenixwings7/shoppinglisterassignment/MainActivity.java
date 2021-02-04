package com.phoenixwings7.shoppinglisterassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private PlaceholderPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.view_pager);

        pagerAdapter = new PlaceholderPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

    }
}