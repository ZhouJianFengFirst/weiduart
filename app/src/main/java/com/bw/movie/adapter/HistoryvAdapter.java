package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.HistoryBean;
import com.bw.movie.entity.SelectInformBean;
import com.bw.movie.utils.Logger;
import com.example.xlistviewlib.DateUtils;

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
        viewHolder.setText(R.id.history_rv_date,DateUtils.format(Long.parseLong(resultBean.getCreateTime()), "MM-dd HH:mm"));
        viewHolder.setText(R.id.history_rv_name,resultBean.getMovieName());
        viewHolder.setText(R.id.history_rv_ordernumber,"订单号:"+resultBean.getOrderId());
        viewHolder.setText(R.id.history_rv_cinema,"影院:"+resultBean.getCinemaName());
        viewHolder.setText(R.id.history_rv_moviehall,"影厅:"+resultBean.getScreeningHall());
        viewHolder.setText(R.id.history_rv_time,"时间:"+resultBean.getBeginTime()+"-"+resultBean.getEndTime());
        viewHolder.setText(R.id.history_rv_num,"数量:"+resultBean.getAmount()+"");
        viewHolder.setText(R.id.history_rv_price,"金额:"+resultBean.getPrice()+"元");
        Logger.i("购买状态",resultBean.getStatus()+"");
        //判断赋值 购票状态 1=待付款 2=已付款
        if("1".equals(resultBean.getStatus()+"")){
            //显示
            viewHolder.setVisibilityText(R.id.history_rv_payment,View.VISIBLE);
        }else if("2".equals(resultBean.getStatus()+"")){
           //隐藏
            viewHolder.setVisibilityText(R.id.history_rv_payment,View.GONE);
        }
    }
}
