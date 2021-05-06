package com.wow.carlauncher.widget.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

public class AppUtil {
    public static boolean isDefaultLauncher(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addCategory("android.intent.category.DEFAULT");
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (str == null || !str.equals(resolveActivity.activityInfo.packageName)) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
