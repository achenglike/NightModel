package cn.like.nightmodel.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by like on 16/7/21.
 */
public class PersistenceUtils {

    public static final String KEY_CURRENT_MODEL = "them_day_night";

    public static boolean isNightModel(Context context) {
        SharedPreferences sp = context.getSharedPreferences(KEY_CURRENT_MODEL, Context.MODE_PRIVATE);
        return sp.getBoolean(KEY_CURRENT_MODEL, false);
    }

    public static void setNightModel(Context context, boolean isNight) {
        SharedPreferences sp = context.getSharedPreferences(KEY_CURRENT_MODEL, Context.MODE_PRIVATE);
        sp.edit().putBoolean(KEY_CURRENT_MODEL, isNight).apply();
    }
}
