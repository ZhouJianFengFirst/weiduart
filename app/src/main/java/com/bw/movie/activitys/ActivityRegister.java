package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityRegisterPersenter;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityBeginPersenter(注册页)
 */
public class ActivityRegister extends BaseActivity<ActivityRegisterPersenter> {


    @Override
    public Class<ActivityRegisterPersenter> getDelegateClass() {
        return ActivityRegisterPersenter.class;
    }
}
