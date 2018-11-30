package com.bw.movie.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityBuyTicketPersenter;

public class ActivityBuyTicket extends BaseActivity<ActivityBuyTicketPersenter>{

    @Override
    public Class<ActivityBuyTicketPersenter> getDelegateClass() {
        return ActivityBuyTicketPersenter.class;
    }
}