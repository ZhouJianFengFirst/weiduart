package com.bw.movie.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;

public class SexBox extends RelativeLayout{
    private boolean man_selected = false;
    private boolean woman_selected = false;
    private ImageView imgman, imgwoman;
    private TextView mantext, womantext;

    public SexBox(Context context) {
        super(context);
        initContext(context);
    }

    public SexBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContext(context);


    }

    public SexBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContext(context);

    }

    private void initContext(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_view_sexbox, this);
        mantext = (TextView) findViewById(R.id.man_tx);
        womantext = (TextView) findViewById(R.id.woman_tx);
        imgman = (ImageView) findViewById(R.id.register_img_man);
        imgwoman = (ImageView) findViewById(R.id.register_img_woman);

        imgman.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getstatu())
                {
                    case 0:
                    case 2:
                        turnToMan();
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            }
        });
        imgwoman.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getstatu())
                {
                    case 0:
                    case 1:
                        turnToWoman();
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });

    }


    public void turnToWoman() {
        imgman.setImageResource(R.drawable.log_icon_man_no);
        imgwoman.setImageResource(R.drawable.log_icon_woman_yes);
        man_selected = false;
        woman_selected = true;
        sexBoxListener.getSex(2);
    }

    public void turnToMan() {
        imgman.setImageResource(R.drawable.log_icon_man_yes);
        imgwoman.setImageResource(R.drawable.log_icon_woman_no);
        man_selected = true;
        woman_selected = false;
        sexBoxListener.getSex(1);
    }

    //判断目前状态
    public int getstatu() {
        //均未选择
        if (!man_selected && !woman_selected) {
            return 0;
        } else if (man_selected && !woman_selected)   //选男
        {
            return 1;
        } else if (!man_selected && woman_selected)  //选女
        {
            return 2;
        } else    //均选择 不可能发生
        {
            return -1;
        }
    }

    public interface SexBoxListener {
        void getSex(int sex);
    }

    private SexBoxListener sexBoxListener;

    public void setSexBoxListener(SexBoxListener sexBoxListener) {
        this.sexBoxListener = sexBoxListener;
    }
}
