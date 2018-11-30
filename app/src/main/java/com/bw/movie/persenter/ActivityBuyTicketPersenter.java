package com.bw.movie.persenter;

import android.content.Context;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;

public class ActivityBuyTicketPersenter extends AppDelegate {
    private Context context;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_buy_ticket;
    }

    @Override
    public void initContext(Context context) {
        this.context=context;
    }

    @Override
    public void initData() {
        super.initData();


    }
}
