package com.bw.movie.activitys;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityMessagePersenter;
import com.bw.movie.utils.SpUtil;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityMessage(我的信息页面)
 * */

//手机号:15711263757   密码:123456

//继承BaseActivity 泛型本页面的persenter类
public class ActivityMessage extends BaseActivity<ActivityMessagePersenter> {

    @Override
    public Class<ActivityMessagePersenter> getDelegateClass() {
        //返回本页面的Persenter
        return ActivityMessagePersenter.class;
    }
    //重写生命周期onResume 聚焦方法
    @Override
    public void onResume() {
        super.onResume();

//        //获取sp里面的数据userId sessionId 调用工具类强转自己需要的类型 提上去调用
        String message1 = (String) SpUtil.getSpData(this,"message", "");
        String status1 = (String) SpUtil.getSpData(this,"status", "");
        String sessionId1 = (String) SpUtil.getSpData(this,"sessionId", "");
        String userId1 = (String) SpUtil.getSpData(this,"userId", "");
        String headPic1 = (String)SpUtil.getSpData(this,"headPic", "");
        String nickName1 = (String) SpUtil.getSpData(this,"nickName", "");
        String phone1 = (String) SpUtil.getSpData(this,"phone", "");
        String birthday1 = (String) SpUtil.getSpData(this,"birthday", "");
        String id1 = (String) SpUtil.getSpData(this,"id", "");
        String lastLoginTime1 = (String) SpUtil.getSpData(this,"lastLoginTime", "");
        String sex1 = (String) SpUtil.getSpData(this,"sex", "");
        //调用方法传获取到的值
        delegate.setData(message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1);
    }

    //回调方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判读requestcode
        switch (requestCode){
            case 0:
                //当时0的时候调用方法生成到persenter 传resultcode 和data相机回调
                delegate.onCode0(resultCode,data);
                break;
            case 1:
                //当时0的时候调用方法生成到persenter 传resultcode 相册回调
                delegate.onCode1(resultCode);
                break;
            case 2:
                //当时0的时候调用方法生成到persenter 传rdata 裁剪回调
                delegate.onCode2(data);
                break;
        }
    }
}
