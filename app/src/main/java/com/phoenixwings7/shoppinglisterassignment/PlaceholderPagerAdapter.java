package com.phoenixwings7.shoppinglisterassignment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlaceholderPagerAdapter extends FragmentStateAdapter {
    private final int tabs_num = MainActivity.TAB_TITLES_IDS.length;

    public PlaceholderPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a new Fragment instance with specific args
        PlaceholderFragment fragment = new PlaceholderFragment();

        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabs_num;
    }

}
