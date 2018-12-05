package com.bw.movie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.SelectInformBean;
import com.example.xlistviewlib.DateUtils;

/**
 * 作者：mafuyan
 * 时间：2018/11/30
 * 作用：系统消息通知适配器
 */

public class InformRvAdapter extends RecycleAdapter<SelectInformBean.ResultBean> {

    private OnShowListener onShowListener;

    public InformRvAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        //返回条目布局
        return R.layout.inform_rv_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final SelectInformBean.ResultBean resultBean, int postion) {
        //给控件赋值内部类 传两个参数前面控件后面集合里的内容
        viewHolder.setText(R.id.inform_rv_title, resultBean.getTitle());
        viewHolder.setText(R.id.inform_rv_desc, resultBean.getContent());
        //赋值时间+空字符串
        viewHolder.setText(R.id.inform_rv_date, DateUtils.format(resultBean.getPushTime(), "yyyy-MM-dd HH:mm"));
        //判断是否已读 0是未读 1是已读
        if (resultBean.getStatus() == 0) {
            //未读的的话就显示
            viewHolder.setVisibilityText(R.id.inform_rv_img, View.VISIBLE);
        } else {
            //读了的的话就隐藏
            viewHolder.setVisibilityText(R.id.inform_rv_img, View.GONE);
        }

        //条目点击事件传id 两个参数一个new 一个点击事件的id
        viewHolder.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断集合status ==0 0未读1 已读
                if (resultBean.getStatus() == 0) {
                    //如果未读 点击后是读了
                    resultBean.setStatus(1);
                } else {
                    resultBean.setStatus(0);
                }
                //定义接口回调 传集合id
                onShowListener.success(resultBean.getId());
                //刷新适配器
                notifyDataSetChanged();
            }
        }, R.id.inform_layout_show);
    }

    //回调接口
    public void setOnShowListener(OnShowListener onShowListener) {
        //接口返回值 this.提上去
        this.onShowListener = onShowListener;
    }

    //接口
    public interface OnShowListener {
        //传方法传id
        void success(int index);
    }
}
