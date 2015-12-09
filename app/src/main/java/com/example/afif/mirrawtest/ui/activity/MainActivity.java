package com.example.afif.mirrawtest.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.afif.mirrawtest.R;
import com.example.afif.mirrawtest.ui.DrawerFragment;
import com.example.afif.mirrawtest.ui.PhotosFragment;
import com.example.afif.mirrawtest.ui.PostsFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolBar;
    private HashMap<Integer, Fragment> mFragmentsMap = new HashMap<>();
    private Fragment mFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentNavigationDrawer);
        drawerFragment.setDrawerLayout(mDrawerLayout, mToolBar);
        openFragment(0);
    }

    public void openFragment(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position) {

            case 0:
                getSupportActionBar().setTitle(getString(R.string.posts));
                mFragment = fragmentManager.findFragmentByTag(PostsFragment.TAG);
                if (mFragment == null) {
                    showNewFragment(0, new PostsFragment(), PostsFragment.TAG);
                } else {
                    showExistingFragment(mFragment, 0);
                }
                break;

            case 1:
                getSupportActionBar().setTitle(getString(R.string.photos));
                mFragment = fragmentManager.findFragmentByTag(PhotosFragment.TAG);
                if (mFragment == null) {
                    showNewFragment(1, new PhotosFragment(), PhotosFragment.TAG);
                } else {
                    showExistingFragment(mFragment, 1);
                }
        }
    }

    private void showNewFragment(int tabIndex, Fragment fragment, String tag) {
        addFragmentToContainer(fragment, tag);
        addFragmentToMap(tabIndex, fragment);
        hideOtherFragments(tabIndex);
    }

    private void addFragmentToMap(int tabIndex, Fragment fragment) {
        if (mFragmentsMap.get(tabIndex) == null) {
            mFragmentsMap.put(tabIndex, fragment);
        }
    }

    private void addFragmentToContainer(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, tag).commit();
    }

    private void showExistingFragment(Fragment fragment, int tabIndex) {
        hideOtherFragments(tabIndex);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.show(fragment);
        ft.commit();
    }

    private void hideOtherFragments(int tabIndex) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        for (Map.Entry<Integer, Fragment> entry : mFragmentsMap.entrySet()) {
            int currentTabIndex = entry.getKey();
            fragment = entry.getValue();
            if (currentTabIndex != tabIndex) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

}
