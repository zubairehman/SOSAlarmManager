package com.cubivue.base.modules.baseclasses.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cubivue.base.R;

public class FragmentUtil {

    private FragmentManager fm;
    private AppCompatActivity activity;

    public FragmentUtil(AppCompatActivity _activity) {
        activity = _activity;
        fm = activity.getSupportFragmentManager();
    }

    public void goBackFragment() {
        goBackFragment(1);
    }

    public void goBackFragment(int count) {
        int totalFragments = fm.getBackStackEntryCount();
        if (totalFragments < count) {
            activity.finish();
            return;
        }
        // if count greater than zero popbackstack
        try {
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    fm.popBackStack();
                }
            }
        } catch (Exception e) {
        }
    }

    public void removeAllFragments() {
        try {
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
        }
    }

    public void removeCurrentFragment() {
        try {
            fm.popBackStack();
        } catch (Exception e) {
        }
    }

    public void gotoNextFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
       // setAnimation(ft);
        ft.replace(R.id.frameLayoutContainer, fragment);
        ft.addToBackStack("");
        ft.commit();
    }

    public void replaceBaseFragment(Fragment fragment) {
        removeAllFragments();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutContainer, fragment);
        ft.commit();
    }

    public void replaceDrawerFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutDrawer, fragment);
        ft.commit();
    }

    private void setAnimation(FragmentTransaction ft) {
        ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left,
                R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

}
