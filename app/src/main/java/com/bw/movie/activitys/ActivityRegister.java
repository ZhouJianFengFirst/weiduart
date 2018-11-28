package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityRegisterPersenter;

public class ActivityRegister extends BaseActivity<ActivityRegisterPersenter> {


    @Override
    public Class<ActivityRegisterPersenter> getDelegateClass() {
        return ActivityRegisterPersenter.class;
    }
}
