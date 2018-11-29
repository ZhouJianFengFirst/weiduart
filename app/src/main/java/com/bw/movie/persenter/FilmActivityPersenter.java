package com.bw.movie.persenter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.example.xlistviewlib.XListView;

public class FilmActivityPersenter extends AppDelegate implements View.OnClickListener{

    private Context context;
    private TextView txtHortmovie,txtHortShowing,txtUpcoming;
    private XListView listView ;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_film;
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initWeight();
    }

    private void initWeight() {
        //初始化控件
        txtHortmovie = (TextView)getView(R.id.txt_hortmovie);
        txtHortShowing = (TextView)getView(R.id.txt_hortshowing);
        txtUpcoming = (TextView)getView(R.id.txt_upcoming);
        listView = (XListView)getView(R.id.xshowlist);
        setClick(this,R.id.txt_hortmovie,R.id.txt_hortshowing,R.id.txt_upcoming);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_hortmovie:
                txtHortmovie.setBackgroundColor(R.drawable.purplechange);
                txtHortShowing.setBackgroundColor(R.drawable.my_item);
                txtUpcoming.setBackgroundColor(R.drawable.my_item);
                break;
            case R.id.txt_hortshowing:
                txtHortmovie.setBackgroundColor(R.drawable.my_item);
                txtHortShowing.setBackgroundColor(R.drawable.purplechange);
                txtUpcoming.setBackgroundColor(R.drawable.my_item);
                break;
            case R.id.txt_upcoming:
                txtHortmovie.setBackgroundColor(R.drawable.my_item);
                txtHortShowing.setBackgroundColor(R.drawable.my_item);
                txtUpcoming.setBackgroundColor(R.drawable.purplechange);
                break;
        }
    }
}
