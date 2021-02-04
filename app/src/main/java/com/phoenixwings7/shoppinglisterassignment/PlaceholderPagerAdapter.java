package com.phoenixwings7.shoppinglisterassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlaceholderPagerAdapter extends FragmentStateAdapter {
    private static final int[] TAB_TITLES_IDS = {R.string.tab_1, R.string.tab_2};

    public PlaceholderPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a new Fragment instance with specific args
        PlaceholderFragment fragment = new PlaceholderFragment();

        Bundle args = new Bundle();
        args.putInt(PlaceholderFragment.ARG_TAB_TITLE_ID, TAB_TITLES_IDS[position]);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return TAB_TITLES_IDS.length;
    }

}
