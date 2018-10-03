package com.embrace.soslibrary.util.fonts;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {

    private FontUtils() {}

    public static Typeface setTypeface(int type, final Context context) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");

        switch (type) {
            case 0:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");
                break;
            case 1:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BlackItalic.ttf");
                break;
            case 2:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
                break;
            case 3:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-BoldItalic.ttf");
                break;
            case 4:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Italic.ttf");
                break;
            case 5:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                break;
            case 6:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-LightItalic.ttf");
                break;
            case 7:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium.ttf");
                break;
            case 8:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-MediumItalic.ttf");
                break;
            case 9:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                break;
            case 10:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
                break;
            case 11:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-ThinItalic.ttf");
                break;
            case 12:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Bold.ttf");
                break;
            case 13:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-BoldItalic.ttf");
                break;
            case 14:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Italic.ttf");
                break;
            case 15:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Light.ttf");
                break;
            case 16:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-LightItalic.ttf");
                break;
            case 17:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed-Regular.ttf");
                break;
        }

        return font;
    }
}
