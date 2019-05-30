package com.example.cats.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.cats.R;
import com.example.cats.mvp.view.MainView;
import com.example.cats.ui.fragment.CatsListFragment;

public class MainActivity extends AppCompatActivity
    implements MainView {

    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (FrameLayout) findViewById(R.id.container);
        Fragment fragment = CatsListFragment.getInstance();
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
