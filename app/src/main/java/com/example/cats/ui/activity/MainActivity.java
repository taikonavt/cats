package com.example.cats.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.cats.R;
import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.presenter.MainActivityPresenter;
import com.example.cats.mvp.view.MainActivityView;
import com.example.cats.ui.fragment.CatItemFragment;
import com.example.cats.ui.fragment.CatsFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityView {

    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenter.onCreated();
    }

    private void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void setCatItemFragment(Cat cat) {
        CatItemFragment fragment = CatItemFragment.getInstance(cat);
        setFragment(fragment);
    }

    @Override
    public void setCatsFragment(){
        CatsFragment fragment = CatsFragment.getInstance();
        setFragment(fragment);
    }
}
