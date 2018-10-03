package com.embrace.soslibrary.ui.baseclasses.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.embrace.soslibrary.R;
import com.embrace.soslibrary.ui.baseclasses.fragment.FragmentUtil;

public abstract class BaseDrawerActivity extends BaseToolbarActivity {

    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_drawer);
        dl = findViewById(R.id.dl);
        new FragmentUtil(this).replaceDrawerFragment(getDrawerFragment());
    }

    public abstract Fragment getDrawerFragment();

    public void closeDrawer() {
        try {
            dl.closeDrawer(Gravity.LEFT);
        } catch (Exception e) {
        }
    }

    public void openDrawer() {
        try {
            dl.openDrawer(Gravity.LEFT);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        if (dl.isDrawerOpen(Gravity.LEFT)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
}
