/**
 *
 */
package com.miaochegu.util;

import android.content.Context;
import android.widget.Toast;

import com.miaochegu.GettingStartedApp;

/**
 * Toast工具类
 */
public class ToastUtil {

    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, int info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }

    public static void show(String info) {
        Toast.makeText(GettingStartedApp.getInstance(), info, Toast.LENGTH_SHORT).show();
    }
}
