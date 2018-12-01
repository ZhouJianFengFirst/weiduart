package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.HistoryBean;
import com.bw.movie.entity.SelectInformBean;

/**
 * 作者：mafuyan
 * 时间：2018/12/1
 * 作用：购票记录适配器
 */

public class HistoryvAdapter extends RecycleAdapter<HistoryBean.ResultBean> {

    public HistoryvAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        //返回布局条目
        return R.layout.history_rv_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, HistoryBean.ResultBean resultBean, int postion) {
        //赋值控件 日期和数量加个空字符串
        viewHolder.setText(R.id.history_rv_date,resultBean.getBeginTime()+"");
        viewHolder.setText(R.id.history_rv_name,resultBean.getMovieName());
        viewHolder.setText(R.id.history_rv_ordernumber,resultBean.getOrderId());
        viewHolder.setText(R.id.history_rv_cinema,resultBean.getCinemaName());
        viewHolder.setText(R.id.history_rv_moviehall,resultBean.getScreeningHall());
        viewHolder.setText(R.id.history_rv_time,resultBean.getCreateTime()+"-"+resultBean.getEndTime());
        viewHolder.setText(R.id.history_rv_num,resultBean.getAmount()+"");
        viewHolder.setText(R.id.history_rv_price,"金额:"+resultBean.getPrice()+"元");
        //判断赋值 购票状态 1=待付款 2=已付款
        if("1".equals(resultBean.getStatus())){
            //显示
            viewHolder.setVisibilityText(R.id.history_rv_payment,View.VISIBLE);
        }else{
           //隐藏
            viewHolder.setVisibilityText(R.id.history_rv_payment,View.GONE);
        }
    }
}
