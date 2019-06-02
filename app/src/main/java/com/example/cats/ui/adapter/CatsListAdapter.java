package com.example.cats.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cats.R;
import com.example.cats.mvp.model.repository.IImageLoader;
import com.example.cats.mvp.model.repository.ImageLoaderImpl;
import com.example.cats.mvp.presenter.list.ICatListPresenter;
import com.example.cats.mvp.view.item.CatItemView;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_cat, viewGroup, false);
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
        private ImageView image;
        private TextView name;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_cat_image);
            name = itemView.findViewById(R.id.item_cat_name_tv);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setImage(String pictureUrl){
            image.setImageResource(R.drawable.ic_android_black_24dp);
            Log.d("mytag", this.toString());
            imageLoader.loadInto(pictureUrl, image, 128);
        }

        @Override
        public void setName(String string){
            name.setText(string);
        }
    }
}
