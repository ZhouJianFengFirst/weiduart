package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityAttentionPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityAttention(我的关注页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityAttention extends BaseActivity<ActivityAttentionPersenter> {

    @Override
    public Class<ActivityAttentionPersenter> getDelegateClass() {
        //返回本页面的Persenter
        return ActivityAttentionPersenter.class;
    }
}
