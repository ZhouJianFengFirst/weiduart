package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 作者：jiangshi
 * 时间：2018/11/30
 * 作用：SpUtil
 */

public class SharedUtil {
    private static String SPCONFIG = "configs";
    private static SharedPreferences sp;

    public static void put(Context context, String key, Object value) {
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
        Editor ed = sp.edit();
        String type = value.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            ed.putInt(key, (Integer) value);
        } else if ("Float".equals(type)) {
            ed.putFloat(key, (Float) value);
        } else if ("Boolean".equals(type)) {
            ed.putBoolean(key, (Boolean) value);
        } else if ("Long".equals(type)) {
            ed.putLong(key, (Long) value);
        } else if ("String".equals(type)) {
            ed.putString(key, (String) value);
        }
        ed.commit();

    }
//15711263757
    public static String getString(Context context, String key){
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
       return sp.getString(key,"");
    }

    public static int getInt(Context context, String key){
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }

    public static boolean getBoolean(Context context, String key){
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }

}