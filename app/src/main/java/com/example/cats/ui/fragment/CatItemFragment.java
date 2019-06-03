package com.example.cats.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cats.R;
import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.model.repository.IImageLoader;
import com.example.cats.mvp.model.repository.ImageLoaderImpl;
import com.example.cats.ui.activity.MainActivity;

public class CatItemFragment extends Fragment {

    private static String CAT_KEY = "cat_key";

    private Cat cat;

    public static CatItemFragment getInstance(Cat cat) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CAT_KEY, cat);
        CatItemFragment fragment = new CatItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cat = getArguments().getParcelable(CAT_KEY);
        } else {
            cat = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.fragment_item_image);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        IImageLoader imageLoader = new ImageLoaderImpl();
        if (cat != null) {
            imageLoader.loadInto(cat.getPictureUrl(), imageView, width);
        }
        ((MainActivity) getActivity()).setTitle(cat.getName());
        return view;
    }
}
