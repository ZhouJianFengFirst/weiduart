package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityMessagePersenter;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityMessage(我的信息页面)
 * */

//继承BaseActivity 泛型本页面的persenter类
public class ActivityMessage extends BaseActivity<ActivityMessagePersenter> {

    @Override
    public Class<ActivityMessagePersenter> getDelegateClass() {
        //返回本页面的Persenter
        return ActivityMessagePersenter.class;
    }
}
