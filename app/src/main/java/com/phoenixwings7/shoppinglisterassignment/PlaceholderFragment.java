package com.phoenixwings7.shoppinglisterassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceholderFragment extends Fragment implements MainMVP.PlaceholderFragment {
    public static final String ARG_TAB_INDEX = "tabIndex";
    private int tabIndex;
    private MainMVP.Presenter mainActivityPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.tabIndex = savedInstanceState.getInt(ARG_TAB_INDEX, 0);
        }
        setFragmentContentObserver();

    }

    public void setPresenterRef(MainMVP.Presenter presenter) {
        this.mainActivityPresenter = presenter;
    }

    @Override
    public void setFragmentContentObserver() {
        LiveData<List<ShoppingList>> shoppingLists = mainActivityPresenter.getPlaceholderFragmentsContentFromDB(tabIndex);
        shoppingLists.observe(getViewLifecycleOwner(), this::updateFragmentContent);
    }

    @Override
    public void updateFragmentContent(List<ShoppingList> shoppingLists) {
        //TODO:update content
    }

}