package com.cubivue.base.modules.baseclasses.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

public abstract class BaseFragment extends Fragment {

    private boolean isActive;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getArguments();
        if (extras != null) {
            getExtras((ArrayList<Object>) extras.getSerializable("extras"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    public void onPause() {
        isActive = false;
        super.onPause();
    }

    public abstract void getExtras(ArrayList<Object> extras);

    public BaseFragment addExtras(ArrayList<Object> extras) {
        if (extras != null) {
            Bundle args = new Bundle();
            args.putSerializable("extras", extras);
            this.setArguments(args);
        }
        return this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public boolean isThisFragmentActive() {
        return isActive;
    }

}
