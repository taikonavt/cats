package com.example.cats.ui.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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
import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.presenter.CatsFragmentPresenter;
import com.example.cats.mvp.view.CatsFragmentView;
import com.example.cats.mvp.view.MainActivityView;
import com.example.cats.ui.activity.MainActivity;
import com.example.cats.ui.adapter.CatsListAdapter;

import java.util.List;

public class CatsFragment extends Fragment implements CatsFragmentView {

    private RecyclerView recyclerView;
    private CatsListAdapter adapter;
    private CatsFragmentPresenter presenter;

    public static CatsFragment getInstance(){
        CatsFragment fragment = new CatsFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new CatsFragmentPresenter(this);

        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        adapter = new CatsListAdapter(presenter.getCatListPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setCatItemFragment(Cat cat) {
        ((MainActivityView) getView()).setCatItemFragment(cat);
    }

    @Override
    public void observeData(LiveData<List<Cat>> data) {
        data.observe(this, new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> catList) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
