package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.entity.DiscussBean;
import com.example.xlistviewlib.DateUtils;

/**
 * 作者：gaojiabao
 * 时间：2018/11/30 9:11
 * 作用：影院评论 列表
 */
public class DiscussAdapter extends RecycleAdapter<DiscussBean.ResultBean> {
    private SetOnItem setOnItem;

    public DiscussAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.discuss_item;
    }

    @Override
    protected void convert(ViewHolder viewHolder, final DiscussBean.ResultBean resultBean, int postion) {
        viewHolder.setSimpleDraweViewUrl(R.id.right_cinema_simp, resultBean.getCommentHeadPic());
        viewHolder.setText(R.id.right_cinema_name, resultBean.getCommentUserName());
        viewHolder.setText(R.id.right_cinema_pl, resultBean.getCommentContent());
        viewHolder.setText(R.id.right_cinema_time, DateUtils.format(resultBean.getCommentTime(), "yyyy-MM-dd HH:mm"));
        viewHolder.setText(R.id.right_cinema_zs, resultBean.getGreatNum() + "");
        if (resultBean.getIsGreat() == 1) {//判断是否点过赞
            viewHolder.setImageResource(R.id.image_cinema_z, R.mipmap.cinema_praise_yes);
        } else if(resultBean.getIsGreat() == 0) {
            viewHolder.setImageResource(R.id.image_cinema_z, R.mipmap.cinema_praise_no);
        }
        //点赞的点击事件
        viewHolder.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultBean.getIsGreat() == 0) {//判断是否点赞
//                    if (resultBean.getGreatNum() > 0) {//判断赞是否大于0
//                        resultBean.setGreatNum(resultBean.getGreatNum() - 1);
//                    }
//                    resultBean.setIsGreat(0);//设置点赞状态
//                } else {
                    //数量加一
                    resultBean.setGreatNum(resultBean.getGreatNum() + 1);
                    resultBean.setIsGreat(1);//设置点赞状态
                }
                //调用接口传递值
                setOnItem.success(resultBean,resultBean.getCommentId());
                notifyDataSetChanged();
            }
        }, R.id.image_cinema_z);
    }

    public void ruselt(SetOnItem setOnItem){
     this.setOnItem=setOnItem;
    }

    public interface SetOnItem{
        void success(DiscussBean.ResultBean resultBean,int dzId);
    }


}
