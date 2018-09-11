package com.cubivue.base.modules.baseclasses.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.cubivue.base.R;
import com.cubivue.base.modules.baseclasses.fragment.FragmentUtil;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.base_activity);
        new FragmentUtil(this).replaceBaseFragment(getBaseFragment());

        //this line is to lock screen orientation
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            getExtras((ArrayList<Object>) extras.getSerializable("extras"));
        }
    }

    public abstract void getExtras(ArrayList<Object> extras);

    public abstract Fragment getBaseFragment();

}
