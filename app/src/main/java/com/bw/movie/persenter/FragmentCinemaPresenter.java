package com.bw.movie.persenter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.HttpHelper;

import java.util.HashMap;

/**
 * 作者：gaojiabao
 * 时间：2018/11/27 18:52
 * 作用：电影院页面
 */
public class FragmentCinemaPresenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private TextView recommend;
    private TextView nearby;
    private FrameLayout frag;
    private ListView list1;
    private String reurl="/movieApi/cinema/v1/findRecommendCinemas";
    private String neurl="";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cinema;
    }

    @Override
    public void initContext(Context context) {
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        dohttp();


    }
   //请求网络
    private void dohttp() {
        HashMap map = new HashMap();
        map.put("userId","18");
        map.put("sessionId","15320748258726");
        map.put("page","1");
        map.put("count","20");
        //请求推荐
        getString(reurl,0,map);
        //请求附近
        getString(neurl,1,null);
    }
   //请求成功返回
    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type){
            case 0:
                Log.i("推荐影院",data);
                break;
            case 1:

                break;
        }
    }

    //找控件的方法
    private void initwidget() {
        recommend = (TextView) getView(R.id.text_cinema_recommend);
        nearby = (TextView) getView(R.id.text_cinema_nearby);
        list1 = (ListView) getView(R.id.listview_cinema_list1);
        setClick(this, R.id.text_cinema_recommend, R.id.text_cinema_nearby);
    }
//点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_cinema_recommend:

                break;

            case R.id.text_cinema_nearby:

                break;
        }
    }
}
