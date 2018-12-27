package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBuyTicket;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.CinemaFlowBean;
import com.bw.movie.entity.CinemaSessionBean;

import java.math.BigDecimal;

/**
 * 作者：gaojiabao
 * 时间：2018/11/30 9:10
 * 作用：影院和影片场次适配器
 */
public class CinemaSessionsAdapter extends RecycleAdapter<CinemaSessionBean.ResultBean> {

    private Context context;

    public CinemaSessionsAdapter(Context mcontext) {
        super(mcontext);
        this.context = mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cinema_session_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final CinemaSessionBean.ResultBean resultBean, int postion) {
        int num = 23;
        num = num + (10 * postion);
        viewHolder.setText(R.id.text_seccion_te1, resultBean.getScreeningHall());
        viewHolder.setText(R.id.text_seccion_te2, resultBean.getBeginTime());
        viewHolder.setText(R.id.text_seccion_te3, resultBean.getEndTime() + "   end");
        viewHolder.setText(R.id.text_seccion_te4, BigDecimal.valueOf(resultBean.getPrice()) + "");
        viewHolder.setClick(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.back(postion);
            }
        }, R.id.cinema_session_lay);
    }

    private BackClickListener listener;

    public void setListener(BackClickListener listener) {
        this.listener = listener;
    }

    public interface BackClickListener {
        void back(int postion);
    }

}
