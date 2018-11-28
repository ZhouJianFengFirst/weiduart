package com.bw.movie.utils;

import android.util.Log;

public    class Logger    {
    private static boolean flag = true;

    public static void i(String tag,String msg){
        if (flag){
            Log.i(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if (flag){
            Log.d(tag,msg);
        }
    }
    public static void v(String tag,String msg){
        if (flag){
            Log.v(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if (flag){
            Log.w(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if (flag){
            Log.e(tag,msg);
        }
    }

}
