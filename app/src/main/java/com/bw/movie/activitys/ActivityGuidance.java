package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityGuidancePersenter;
/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：ActivityGuidance(引导页)
 * */
public class ActivityGuidance extends BaseActivity<ActivityGuidancePersenter> {


    @Override
    public Class<ActivityGuidancePersenter> getDelegateClass() {
        return ActivityGuidancePersenter.class;
    }
}
