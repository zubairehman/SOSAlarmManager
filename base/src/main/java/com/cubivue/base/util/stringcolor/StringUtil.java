package com.cubivue.base.util.stringcolor;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.telephony.PhoneNumberUtils;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

public class StringUtil {

    public static String getStringFromResId(Context context, @StringRes int resId) {
        String result = "";
        try {
            result = context.getString(resId);
        } catch (Exception e) {
        }
        return result;
    }

    public static String getUtf8Encoded(String str) {
        try {
            return (URLEncoder.encode(str, "UTF-8"));
        } catch (Exception e) {
        }
        return str;
    }

    public static CharSequence[] getFromArrayList(List<String> vals) {
        CharSequence[] cs = vals.toArray(new CharSequence[vals.size()]);
        return cs;
    }

    public static int getResId(String resNameString) {

        try {
            Field idField = String.class.getDeclaredField(resNameString);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String loadJSONFromAsset(Activity activity, String filename) {
        String json = null;
        try {

            InputStream is = activity.getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (Exception ex) {
            return null;
        }
        return json;

    }

    public static boolean isEmpty(String val) {
        if (val == null) return true;
        if (val.isEmpty()) return true;
        if (val.trim().isEmpty()) return true;
        return false;
    }

    public static boolean isEmailValid(String email) {
        if (isEmpty(email)) return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (isEmpty(phoneNumber)) return false;
        if (PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)) {
            return phoneNumber.length() == 11; // fx: +4512345678
        }
        return false;
    }

    public static boolean isZipcodeValid(String zipCode) {
        if (isEmpty(zipCode)) return false;
        return zipCode.length() == 4;
    }

    public static boolean isPasswordValid(String password) {
        if (isEmpty(password)) return false;
        return password.length() >= 8;
    }

    public static boolean validateForLogin(TextView tvUsername, TextView tvPassword) {
        boolean isValid = true;

        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        if (username == null || username.trim().isEmpty()) {
            tvUsername.setError("Username not correct");
            isValid = false;
        }

        if (password == null || password.trim().isEmpty()) {
            tvPassword.setError("Password not correct");
            isValid = false;
        }

        return isValid;
    }

}
