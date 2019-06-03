package com.example.cats.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.cats.R;
import com.example.cats.mvp.model.entity.Cat;
import com.example.cats.mvp.presenter.MainActivityPresenter;
import com.example.cats.mvp.view.MainActivityView;
import com.example.cats.ui.fragment.CatItemFragment;
import com.example.cats.ui.fragment.CatsFragment;

public class MainActivity extends AppCompatActivity
    implements MainActivityView {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        if (savedInstanceState == null){
            presenter.onFirstCreate();
        }
    }

    @Override
    public void setCatItemFragment(Cat cat) {
        CatItemFragment fragment = CatItemFragment.getInstance(cat);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setCatsFragment(){
        CatsFragment fragment = CatsFragment.getInstance();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
