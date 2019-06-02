package com.example.cats.ui.fragment;

import android.support.v4.app.Fragment;

import com.example.cats.mvp.model.entity.Cat;

public class CatItemFragment extends Fragment {

    public static CatItemFragment getInstance(Cat cat) {
        CatItemFragment fragment = new CatItemFragment();
        return fragment;
    }



}
