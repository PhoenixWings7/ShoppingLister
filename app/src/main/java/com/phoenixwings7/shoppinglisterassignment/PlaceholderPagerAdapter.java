package com.phoenixwings7.shoppinglisterassignment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PlaceholderPagerAdapter extends FragmentStateAdapter {
    private final int tabs_num = MainActivity.TAB_TITLES_IDS.length;
    private final MainMVP.Presenter mainActivityPresenter;

    public PlaceholderPagerAdapter(@NonNull FragmentActivity fragmentActivity, MainMVP.Presenter activityPresenter) {
        super(fragmentActivity);
        this.mainActivityPresenter = activityPresenter;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a new Fragment instance with specific args
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(PlaceholderFragment.ARG_TAB_INDEX, position);

        fragment.setArguments(args);
        fragment.setPresenterRef(mainActivityPresenter);

        return fragment;
    }

    @Override
    public int getItemCount() {
        return tabs_num;
    }

}
