package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> views = new SparseArray<>();
    private View rootView;

    public ViewHolder(@NonNull View itemView, Context mcontext) {
        super(itemView);
        this.mContext = mcontext;
        rootView = itemView;

    }


    public View getRootView(){
        return rootView;
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

   /* public ViewHolder setImageUrl(int viewId, String url) {
        ImageView imageView = (ImageView) getView(viewId);
        Picasso.with(mContext).load(url).into(imageView);
        return this;
    }*/

    //如果想添加其他的方法请在下方添加参照setText如获取网络图片等....
}