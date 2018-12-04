package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.recycle.RecycleAdapter;
import com.bw.movie.base.viewholder.ViewHolder;
import com.bw.movie.contract.Contract;
import com.bw.movie.entity.CommentEntity;
import com.bw.movie.utils.DateUtils;
import com.bw.movie.utils.Logger;
import com.facebook.drawee.view.SimpleDraweeView;

public class CommentAdapter extends RecycleAdapter<CommentEntity.ResultBean> {

    private int flag = 0;

    public CommentAdapter(Context mcontext) {
        super(mcontext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_iteam_comment;
    }

    @Override
    protected void convert(ViewHolder viewHolder, CommentEntity.ResultBean resultBean, int postion) {
        Logger.d("Tagger1",resultBean.getIsGreat()+"");
        flag = resultBean.getIsGreat();
        viewHolder.setText(R.id.txt_username, resultBean.getCommentUserName())
                .setText(R.id.txt_content, resultBean.getCommentContent())
                .setText(R.id.txt_praise, resultBean.getGreatNum() + "")
                .setText(R.id.txt_messageall, resultBean.getReplyNum() + "")
                .setText(R.id.txt_time, DateUtils.format(resultBean.getCommentTime(), "yyyy-MM-dd"));
        SimpleDraweeView simpleDraweeView = viewHolder.getView(R.id.sm_user_image);
        simpleDraweeView.setImageURI(resultBean.getCommentHeadPic());
        if (0 == flag) {
            viewHolder.setImageResource(R.id.iv_praise, R.mipmap.cinema_praise_no);
        } else {
            viewHolder.setImageResource(R.id.iv_praise, R.mipmap.cinema_praise_yes);
        }
        viewHolder.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.backFlag(flag, resultBean.getCommentId());
                viewHolder.setImageResource(R.id.iv_praise, R.mipmap.cinema_praise_yes);
            }
        }, R.id.iv_praise);
    }

    private Contract.BackFlagListener listener;

    public void setListener(Contract.BackFlagListener listener) {
        this.listener = listener;
    }
}
