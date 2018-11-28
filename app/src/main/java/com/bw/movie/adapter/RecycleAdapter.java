package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class RecycleAdapter<T> extends RecyclerView.Adapter<ViewHolder> {


    private List<T> list = new ArrayList<>();
    private Context mcontext;
    private int postion;

    public RecycleAdapter(Context mcontext, List<T> datas) {
        this.mcontext = mcontext;
        this.list = datas;
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