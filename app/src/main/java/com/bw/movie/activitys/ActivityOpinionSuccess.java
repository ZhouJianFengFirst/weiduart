package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityOpinionSuccessPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinionSuccess(我的意见反馈成功页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityOpinionSuccess extends BaseActivity<ActivityOpinionSuccessPersenter> {

    @Override
    public Class<ActivityOpinionSuccessPersenter> getDelegateClass() {
        //返回本页面的persenter类
        return ActivityOpinionSuccessPersenter.class;
    }
}
