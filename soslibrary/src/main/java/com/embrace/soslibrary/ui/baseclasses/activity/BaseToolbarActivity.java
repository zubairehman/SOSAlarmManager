package com.embrace.soslibrary.ui.baseclasses.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.embrace.soslibrary.R;
import com.embrace.soslibrary.ui.baseclasses.fragment.FragmentUtil;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_toolbar);
        new FragmentUtil(this).replaceBaseFragment(getBaseFragment());
    }

    public abstract Fragment getBaseFragment();

}
