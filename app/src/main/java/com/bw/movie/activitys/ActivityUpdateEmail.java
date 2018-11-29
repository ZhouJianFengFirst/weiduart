package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityUpdateEmailPersenter;
import com.bw.movie.utils.SpUtil;

/**
 * 作者：mafuyan
 * 时间：2018/11/29
 * 作用：修改邮箱页面
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityUpdateEmail extends BaseActivity<ActivityUpdateEmailPersenter>{

    @Override
    public Class<ActivityUpdateEmailPersenter> getDelegateClass() {
        //返回本页面的Persenter类
        return ActivityUpdateEmailPersenter.class;
    }
    //重写生命周期onResume 聚焦方法
    @Override
    public void onResume() {
        super.onResume();
//        //获取sp里面的数据userId sessionId 调用工具类强转自己需要的类型 提上去调用
        String message1 = (String) SpUtil.getInserter(this).getSpData("message", "");
        String status1 = (String) SpUtil.getInserter(this).getSpData("status", "");
        String sessionId1 = (String) SpUtil.getInserter(this).getSpData("sessionId()", "");
        String userId1 = (String) SpUtil.getInserter(this).getSpData("userId", "");
        String headPic1 = (String) SpUtil.getInserter(this).getSpData("headPic", "");
        String nickName1 = (String) SpUtil.getInserter(this).getSpData("nickName", "");
        String phone1 = (String) SpUtil.getInserter(this).getSpData("phone", "");
        String birthday1 = (String) SpUtil.getInserter(this).getSpData("birthday", "");
        String id1 = (String) SpUtil.getInserter(this).getSpData("id", "");
        String lastLoginTime1 = (String) SpUtil.getInserter(this).getSpData("lastLoginTime", "");
        String sex1 = (String) SpUtil.getInserter(this).getSpData("sex", "");
        //调用方法传获取到的值
        delegate.setData(message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1);
    }
}
