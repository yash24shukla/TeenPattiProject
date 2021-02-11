package com.teenpatti.queendemo.files;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.razorpay.BuildConfig;

public class BaseUtils {

    public static void showLog(String TAG, String content) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "" + content);
        }
    }

    public static boolean checkNonNull(String text) {
        return text != null && !text.equalsIgnoreCase("null")
                && !text.equalsIgnoreCase("NA")
                && !text.trim().equalsIgnoreCase("-")
                && !text.trim().isEmpty() && !text.equalsIgnoreCase("none");
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
