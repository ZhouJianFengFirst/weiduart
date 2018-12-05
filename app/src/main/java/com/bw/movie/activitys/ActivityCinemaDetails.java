package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityCinemaDetailsPersenter;
import com.bw.movie.utils.SpUtil;

/**
 *作者：gaojiabao
 *时间：2018/11/28 8:51
 *作用：影院详情Activity
 */
public class ActivityCinemaDetails extends BaseActivity<ActivityCinemaDetailsPersenter> {

    @Override
    public Class<ActivityCinemaDetailsPersenter> getDelegateClass() {
        return ActivityCinemaDetailsPersenter.class;
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

}