package com.example.cats.mvp.presenter;

import android.arch.lifecycle.LiveData;

import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.model.repository.Repository;
import com.example.cats.mvp.presenter.list.ICatListPresenter;
import com.example.cats.mvp.view.CatsFragmentView;
import com.example.cats.mvp.view.item.CatItemView;

import java.util.List;

public class CatsFragmentPresenter {

    private CatsFragmentView view;
    private Repository repository;
    private ICatListPresenter catListPresenter;
    private LiveData<List<Cat>> data;

    public CatsFragmentPresenter(CatsFragmentView view){
        this.view = view;
        repository = new Repository();
        data = repository.getCatsList();
        catListPresenter = new CatListPresenter();
        view.observeData(data);
    }

    public ICatListPresenter getCatListPresenter(){
        return catListPresenter;
    }

    private class CatListPresenter implements ICatListPresenter{

        @Override
        public void onCatItemClick(CatItemView itemView) {
            List<Cat> list = data.getValue();
            int pos = itemView.getPos();
            if (list != null) {
                view.setCatItemFragment(list.get(pos));
            }
        }

        @Override
        public void bindView(CatItemView itemView) {
            List<Cat> list = data.getValue();
            int pos = itemView.getPos();
            if (list != null) {
                Cat cat = list.get(pos);
                itemView.setName(cat.getName());
                itemView.setImage(cat.getPictureUrl());
            }
        }

        @Override
        public int getItemCount() {
            if (data == null){
                return 0;
            }
            if (data.getValue() == null) {
                return 0;
            }
            return data.getValue().size();
        }
    }
}
