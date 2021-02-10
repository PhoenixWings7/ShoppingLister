package com.phoenixwings7.shoppinglisterassignment.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phoenixwings7.shoppinglisterassignment.MainMVP;
import com.phoenixwings7.shoppinglisterassignment.R;
import com.phoenixwings7.shoppinglisterassignment.database.ShoppingList;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceholderFragment extends Fragment implements MainMVP.PlaceholderFragment {
    public static final String ARG_TAB_INDEX = "tabIndex";
    private int tabIndex;
    private MainMVP.Presenter mainActivityPresenter;
    private ListPlaceholderAdapter listPlaceholderAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get tabIndex for this fragment
        this.tabIndex = getArguments().getInt(ARG_TAB_INDEX, 0);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set RecyclerView for showing shopping lists
        RecyclerView shoppingListsRecyclerView = view.findViewById(R.id.main_recycler_view);

        listPlaceholderAdapter = new ListPlaceholderAdapter(getContext());
        shoppingListsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        shoppingListsRecyclerView.setAdapter(listPlaceholderAdapter);
        setDataObserver(mainActivityPresenter.getPlaceholderFragmentsContent(tabIndex));
    }

    public void setPresenterRef(MainMVP.Presenter presenter) {
        this.mainActivityPresenter = presenter;
    }

    @Override
    public void setDataObserver(LiveData<List<ShoppingList>> data) {
        data.observe(getViewLifecycleOwner(), shoppingLists -> {
            listPlaceholderAdapter.setData(shoppingLists);
        });
    }

}