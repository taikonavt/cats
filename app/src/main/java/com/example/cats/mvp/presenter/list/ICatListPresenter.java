package com.example.cats.mvp.presenter.list;

import com.example.cats.mvp.view.item.CatItemView;

public interface ICatListPresenter {

    void onCatItemClick(CatItemView itemView);

    void bindView(CatItemView itemView);

    int getItemCount();
}
