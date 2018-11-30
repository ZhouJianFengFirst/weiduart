package com.bw.movie.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：SpUtil
 */

public class SpUtil {
    private static String SPCONFIG = "config_sp";
    private static SharedPreferences sp;
    private static Editor edit;


    // 获取sharedpreferences中的数据
    public static Object getSpData(Context context,String key, Object value) {
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
        edit = sp.edit();
        String type = value.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) value);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) value);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) value);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) value);
        } else if ("String".equals(type)) {
            return sp.getString(key, (String) value);
        }
        return null;
    }

    public static void saveData(Context context,String key, Object value) {
        sp = context.getSharedPreferences(SPCONFIG, context.MODE_PRIVATE);
        edit = sp.edit();
        String type = value.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            edit.putInt(key, (Integer) value);
        } else if ("Float".equals(type)) {
            edit.putFloat(key, (Float) value);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) value);
        } else if ("Long".equals(type)) {
             edit.putLong(key, (Long) value);
        } else if ("String".equals(type)) {
            edit.putString(key, (String) value);
        }
        edit.commit();

    }

//    /*清空*/
//    public static SpUtil clear() {
//        edit.clear();
//        return this;
//    }
}