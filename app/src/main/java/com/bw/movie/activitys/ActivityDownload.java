package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityDownloadPersenter;

/**
 * 作者：mafuyan
 * 时间：2018/12/7
 * 作用：下载apk的Activity
 * */

public class ActivityDownload extends BaseActivity<ActivityDownloadPersenter> {
    @Override
    public Class<ActivityDownloadPersenter> getDelegateClass() {
        return ActivityDownloadPersenter.class;
    }
}