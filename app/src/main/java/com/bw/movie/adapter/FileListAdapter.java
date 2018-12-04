package com.bw.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.contract.Contract;
import com.bw.movie.entity.HortMovieEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class FileListAdapter extends BaseAdapter {

    private List<HortMovieEntity.ResultBean> hortList = new ArrayList<>();

    private Context context;

    public FileListAdapter(Context context) {
        this.context = context;
    }

    public void setHortList(List<HortMovieEntity.ResultBean> hortList) {
        this.hortList = hortList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hortList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_film_iteam, null);
            viewHolder.backImage = convertView.findViewById(R.id.sm_background);
            viewHolder.filmImage = convertView.findViewById(R.id.sm_film_image);
            viewHolder.selectImage = convertView.findViewById(R.id.sm_select);
            viewHolder.txtTiele = convertView.findViewById(R.id.txt_title);
            viewHolder.txtContent = convertView.findViewById(R.id.txt_content);
            viewHolder.layout_heart = convertView.findViewById(R.id.layout_heart);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.backImage.setImageResource(R.drawable.iteamback);
        viewHolder.filmImage.setImageURI(hortList.get(position).getImageUrl());
        if (hortList.get(position).isFollowMovie()) {
            viewHolder.selectImage.setImageResource(R.drawable.heart_no);
        } else {
            viewHolder.selectImage.setImageResource(R.drawable.heart_yes);
        }
        viewHolder.layout_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.followMovie(hortList.get(position).getId(), hortList.get(position).isFollowMovie());
            }
        });
        viewHolder.txtTiele.setText(hortList.get(position).getName());
        viewHolder.txtContent.setText("简介：" + hortList.get(position).getSummary());
        return convertView;
    }

    class ViewHolder {
        private SimpleDraweeView backImage;
        private SimpleDraweeView filmImage;
        private SimpleDraweeView selectImage;
        private TextView txtTiele;
        private TextView txtContent;
        private RelativeLayout layout_heart;
    }

    private Contract.BackDataListener listener;

    public void setListener(Contract.BackDataListener listener) {
        this.listener = listener;
    }
}
