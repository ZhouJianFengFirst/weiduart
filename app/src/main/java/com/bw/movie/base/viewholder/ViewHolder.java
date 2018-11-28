package com.bw.movie.base.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：ViewHolder(recyclerview的Viewholder)
 * */
public class ViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> views = new SparseArray<>();
    View rootView;

    public ViewHolder(@NonNull View itemView, Context mcontext) {
        super(itemView);
        this.mContext = mcontext;
        rootView = itemView;

    }


    public <T extends View> T getView(int viewId) {
        T view = (T) views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public ViewHolder setText(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }

    public ViewHolder setSimpleDraweViewUrl(int viewId, String url) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) getView(viewId);
        simpleDraweeView.setImageURI(url);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resource) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageResource(resource);
        return this;
    }

    public void setClick(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id :ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    //如果想添加其他的方法请在下方添加参照setText如获取网络图片等....
}