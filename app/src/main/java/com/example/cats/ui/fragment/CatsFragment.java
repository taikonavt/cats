package com.example.cats.ui.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cats.R;
import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.presenter.CatsFragmentPresenter;
import com.example.cats.mvp.view.CatsFragmentView;
import com.example.cats.mvp.view.MainActivityView;
import com.example.cats.ui.adapter.CatsListAdapter;

import java.util.List;

public class CatsFragment extends Fragment implements CatsFragmentView {

    private SwipeRefreshLayout refreshView;
    private RecyclerView recyclerView;
    private CatsListAdapter adapter;
    private CatsFragmentPresenter presenter;

    public static CatsFragment getInstance(){
        CatsFragment fragment = new CatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, null);
        presenter = new CatsFragmentPresenter(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_list_rv);
        refreshView = (SwipeRefreshLayout) view.findViewById(R.id.fragment_list_swipe_to_refresh);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        adapter = new CatsListAdapter(presenter.getCatListPresenter());
        recyclerView.setAdapter(adapter);

        getActivity().setTitle(R.string.app_name);
        setListeners();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_refresh:
                refreshView.setRefreshing(true);
                presenter.onMenuRefresh();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onSwipeRefresh();
            }
        });
    }

    @Override
    public void setCatItemFragment(Cat cat) {
        ((MainActivityView) getActivity()).setCatItemFragment(cat);
    }

    @Override
    public void observeData(LiveData<List<Cat>> data) {
        data.observe(this, new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> catList) {
                adapter.notifyDataSetChanged();
                refreshView.setRefreshing(false);
            }
        });
    }
}
