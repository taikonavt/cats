package com.example.cats.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cats.R;
import com.example.cats.mvp.presenter.CatListPresenter;
import com.example.cats.ui.adapter.CatsListAdapter;

public class CatsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CatsListAdapter adapter;
    private CatListPresenter presenter;

    public static CatsListFragment getInstance(){
        CatsListFragment fragment = new CatsListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);

        presenter = new CatListPresenter();

        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        adapter = new CatsListAdapter();
        recyclerView.setAdapter(adapter);

        return view;
    }
}
