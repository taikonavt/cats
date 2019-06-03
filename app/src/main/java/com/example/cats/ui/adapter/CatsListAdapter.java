package com.example.cats.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.cats.R;
import com.example.cats.mvp.model.repository.IImageLoader;
import com.example.cats.mvp.model.repository.ImageLoaderImpl;
import com.example.cats.mvp.presenter.list.ICatListPresenter;
import com.example.cats.mvp.view.item.CatItemView;
import com.example.cats.ui.customviews.CustomView;

public class CatsListAdapter extends RecyclerView.Adapter<CatsListAdapter.MyViewHolder> {

    private ICatListPresenter presenter;
    private IImageLoader imageLoader;

    public CatsListAdapter(ICatListPresenter presenter){
        this.presenter = presenter;
        imageLoader = new ImageLoaderImpl();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = new CustomView(viewGroup.getContext());
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int position) {
        myViewHolder.position = position;
        presenter.bindView(myViewHolder);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCatItemClick(myViewHolder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements CatItemView {
        private int position;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setImage(String pictureUrl){
            CustomView customView = (CustomView) itemView;
            customView.setImageResource(R.drawable.ic_android_black_24dp);
            imageLoader.loadInto(pictureUrl, customView, 128);
        }

        @Override
        public void setName(String string){
            CustomView customView = (CustomView) itemView;
            customView.setName(string);
        }
    }
}
