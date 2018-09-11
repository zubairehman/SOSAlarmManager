package com.cubivue.base.ui.baseclasses.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cubivue.base.R;
import com.cubivue.base.ui.baseclasses.fragment.FragmentUtil;

public abstract class BaseToolbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_toolbar);
        new FragmentUtil(this).replaceBaseFragment(getBaseFragment());
    }

    public abstract Fragment getBaseFragment();

}
