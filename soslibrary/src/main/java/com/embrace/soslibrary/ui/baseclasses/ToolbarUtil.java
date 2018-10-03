package com.embrace.soslibrary.ui.baseclasses;

import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.embrace.soslibrary.R;
import com.embrace.soslibrary.ui.baseclasses.activity.BaseDrawerActivity;
import com.embrace.soslibrary.ui.baseclasses.activity.BaseToolbarActivity;
import com.embrace.soslibrary.ui.baseclasses.fragment.FragmentUtil;
import com.embrace.soslibrary.util.stringcolor.ColorUtil;


public class ToolbarUtil {

    private BaseToolbarActivity activity;
    private Toolbar tb;

    public ToolbarUtil(BaseToolbarActivity _activity) {
        try {
            activity = _activity;
            tb = activity.findViewById(R.id.toolbar);
            activity.setSupportActionBar(tb);
        } catch (Exception e) {
        }
    }

    public void refresh() {
        try {
            tb.removeAllViews();
        } catch (Exception e) {
        }
        setNavigationBtn();
    }

    private void setNavigationBtn() {
        try {
            if (activity instanceof BaseDrawerActivity) {
                int count = activity.getSupportFragmentManager().getBackStackEntryCount();
                int navResIdLocal;
                if (count == 0) {
                    navResIdLocal = R.drawable.ic_menu;
                } else {
                    navResIdLocal = R.drawable.ic_back_btn;
                }
                tb.setNavigationIcon(navResIdLocal);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tb.getNavigationIcon().setTint(ColorUtil.getColor(activity, R.color.white));
                }
                View.OnClickListener onClickListenerLocal;
                if (count == 0) {
                    onClickListenerLocal = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ((BaseDrawerActivity) activity).openDrawer();
                        }
                    };
                } else {
                    onClickListenerLocal = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new FragmentUtil(activity).goBackFragment();
                        }
                    };
                }
                tb.setNavigationOnClickListener(onClickListenerLocal);
            } else if (activity instanceof BaseToolbarActivity) {
                tb.setNavigationIcon(R.drawable.ic_back_btn);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tb.getNavigationIcon().setTint(ColorUtil.getColor(activity, R.color.white));
                }
                View.OnClickListener onClickListenerLocal = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new FragmentUtil(activity).goBackFragment();
                    }
                };
                tb.setNavigationOnClickListener(onClickListenerLocal);
            }
        } catch (Exception e) {
        }
    }

    public void setTitle(String title) {
        try {
            tb.setTitle(title);
        } catch (Exception e) {
        }
    }

    public TextView addMenuItemString(String title, View.OnClickListener onClickListener) {
        try {
            TextView tv = (TextView) activity.getLayoutInflater().inflate(R.layout.menuitem_string, null);
            Toolbar.LayoutParams lp = new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getToolbarHeight());
            lp.gravity = Gravity.RIGHT;
            tv.setLines(1);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setMinWidth(getToolbarHeight());
            tv.setText(title);
            tv.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            if (onClickListener != null) tv.setOnClickListener(onClickListener);
            tb.addView(tv, lp);
            return tv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageView addMenuItemImage(int imgRes, View.OnClickListener onClickListener) {
        try {
            ImageView iv = (ImageView) activity.getLayoutInflater().inflate(R.layout.menuitem_image, null);
            Toolbar.LayoutParams lp = new Toolbar.LayoutParams(getToolbarHeight(), getToolbarHeight());
            lp.gravity = Gravity.RIGHT;
            iv.setImageResource(imgRes);
            if (onClickListener != null) iv.setOnClickListener(onClickListener);
            tb.addView(iv, lp);
            return iv;
        } catch (Exception e) {
        }
        return null;
    }

    public ProgressBar addMenuItemProgress() {
        try {
            ProgressBar pb = (ProgressBar) activity.getLayoutInflater().inflate(R.layout.menuitem_progress, null);
            Toolbar.LayoutParams lp = new Toolbar.LayoutParams(getToolbarHeight(), getToolbarHeight());
            lp.gravity = Gravity.RIGHT;
            tb.addView(pb, lp);
            return pb;
        } catch (Exception e) {
        }
        return null;
    }

    public void show() {
        try {
            tb.setVisibility(View.VISIBLE);
        } catch (Exception e) {
        }
    }

    public void hide() {
        try {
            tb.setVisibility(View.GONE);
        } catch (Exception e) {
        }
    }

    private int getToolbarHeight() {
        try {
            int height = tb.getLayoutParams().height;
            return height;
        } catch (Exception e) {
        }
        return 0;
    }

}
