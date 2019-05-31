package com.example.cats.ui.adapter;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cats.R;
import com.example.cats.mvp.presenter.list.ICatListPresenter;
import com.example.cats.mvp.view.item.CatItemView;

public class CatsListAdapter extends RecyclerView.Adapter<CatsListAdapter.MyViewHolder> {

    private ICatListPresenter presenter;

    public CatsListAdapter(ICatListPresenter presenter){
        this.presenter = presenter;
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
            image = itemView.findViewById(R.id.image_cat);
            name = itemView.findViewById(R.id.tv_name);
        }

        @Override
        public int getPos() {
            return position;
        }

        @Override
        public void setImage(Bitmap bitmap){
            image.setImageBitmap(bitmap);
        }

        @Override
        public void setName(String string){
            name.setText(string);
        }
    }
}
