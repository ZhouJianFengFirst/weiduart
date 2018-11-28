package com.bw.movie.persenter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.net.Http;
import com.bw.movie.utils.SimpDrawViewUtils;
import com.bw.movie.view.PagerAdapter3D;
import com.bw.movie.view.RotationPageTransformer;
import com.bw.movie.view.ViewPage3D;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentFilmPresenter extends AppDelegate {

    private static final int SOONMOVIELIST_CONTENT = 0x123;
    private Context context;
    private ViewPage3D viewPage3D;
    private SimpleDraweeView smBackGroundImage;
    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.context = context;
    }

    @Override
    public void initData() {
        super.initData();
        initWeght();

        //网络请求
        Map<String,String> map = new HashMap<>();
        map.put("page","1");
        map.put("count","10");
        getString(Http.SOONMOVIELIST_URL,SOONMOVIELIST_CONTENT,map);

    }

    private void initWeght() {
        viewPage3D = (ViewPage3D) getView(R.id.viepage_3d);
        smBackGroundImage = (SimpleDraweeView) getView(R.id.backgrpound_image);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_film;
    }


    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type){
            case SOONMOVIELIST_CONTENT:
                Log.d("Tagger",data);
                break;
        }
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        toast("请检查网络");
    }
}