package com.bw.movie.base.recycle;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.base.viewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：RecycleAdapter(recyclerView基类类)
 * */
public abstract class RecycleAdapter<T> extends RecyclerView.Adapter<ViewHolder> {


    private List<T> list = new ArrayList<>();
    private Context mcontext;
    private int postion;

    public RecycleAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setList(List<T> datas) {
        this.list.clear();
        this.list.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mcontext).inflate(getLayoutId(), null);
        return new ViewHolder(view, mcontext);
    }

    protected abstract int getLayoutId();

    protected abstract void convert(ViewHolder viewHolder, T t, int postion);

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        this.postion = i;
        convert(viewHolder, list.get(i), i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
