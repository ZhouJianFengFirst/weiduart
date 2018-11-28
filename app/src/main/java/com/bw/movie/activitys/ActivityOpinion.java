package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityOpinionPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityOpinion(我的意见反馈页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityOpinion extends BaseActivity<ActivityOpinionPersenter> {

    @Override
    public Class<ActivityOpinionPersenter> getDelegateClass() {
        //返回本页面的persenter
        return ActivityOpinionPersenter.class;
    }
}
