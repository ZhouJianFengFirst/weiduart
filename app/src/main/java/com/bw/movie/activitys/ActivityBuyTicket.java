package com.bw.movie.activitys;

import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityBuyTicketPersenter;
/**
*作者：gaojiabao
*时间：2018/12/5 11:35
*作用：选座页面
*/
public class ActivityBuyTicket extends BaseActivity<ActivityBuyTicketPersenter>{

    @Override
    public Class<ActivityBuyTicketPersenter> getDelegateClass() {
        return ActivityBuyTicketPersenter.class;
    }
}