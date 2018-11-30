package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.activitys.MainActivity;
import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBegin;
import com.bw.movie.activitys.ActivityGuidance;
import com.bw.movie.adapter.StartAdapter;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：xujiahui
 * 时间：2018/11/27
 * 作用：ActivityGuidancePersenter(引导页)
 */
public class ActivityGuidancePersenter extends AppDelegate {

    private List<Integer> imgs = new ArrayList<>();
    private Context mcontext;
    private ViewPager vpguidance;
    private Button btjump;
    private LinearLayout linearcircle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guidance;
    }


    @Override
    public void initData() {
        super.initData();
        initwidget();
        boolean isfirst = (boolean) SpUtil.getInserter(mcontext).getSpData("isFirst", false);
        if (isfirst) {
            mcontext.startActivity(new Intent(mcontext, ActivityBegin.class));
            //销毁
            ((ActivityGuidance) mcontext).finish();

        } else {
            SpUtil.getInserter(mcontext).saveData("isFirst", true).commit();
            StartAdapter startAdapter = new StartAdapter(imgs, mcontext);
            vpguidance.setAdapter(startAdapter);
            pointe(0);
            //销毁
        }
    }

    private void initwidget() {
        vpguidance = (ViewPager) getView(R.id.gm_viewpager_guidance);
        btjump = (Button) getView(R.id.gm_bt_jump);
        linearcircle = (LinearLayout) getView(R.id.gm_linearlayout_circle);
        vpguidance.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                pointe(i);
                if (i == imgs.size() - 1) {
                    btjump.setText("立即跳转");
                    btjump.setVisibility(View.VISIBLE);

                    setClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.gm_bt_jump:
                                    mcontext.startActivity(new Intent(mcontext,  ActivityBegin.class));
                                    ((ActivityGuidance) mcontext).finish();

                                    break;
                            }
                        }
                    }, R.id.gm_bt_jump);

                } else {
                    btjump.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        imgs.add(R.drawable.coverage1);
        imgs.add(R.drawable.coverage2);
        imgs.add(R.drawable.coverage3);
        imgs.add(R.drawable.coverage4);
    }

    public void pointe(int page) {
        linearcircle.removeAllViews();
        for (int i = 0; i < imgs.size(); i++) {
            ImageView image = new ImageView(mcontext);
            if (page == i) {
                image.setImageResource(R.drawable.pwp_bg);
            } else {
                image.setImageResource(R.drawable.circle_guidance_no);
            }
            linearcircle.addView(image);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) image.getLayoutParams();
            params.weight = 30;
            params.height = 30;
            image.setLayoutParams(params);
        }
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }
}
