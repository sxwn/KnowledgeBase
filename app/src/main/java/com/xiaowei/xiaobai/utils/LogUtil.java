package com.xiaowei.xiaobai.utils;

import android.util.Log;

public class LogUtil {
    private static final String TAG = "LogUtil";

    public static void d(Object msg) {
        if (msg != null) {
            Log.d(TAG, msg.toString());
        }
    }
}
