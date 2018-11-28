package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityBegin;
import com.bw.movie.activitys.MainActivity;
import com.bw.movie.mvp.view.AppDelegate;

/**
 * 作者：xujiahui
 * 时间：2018/11/28
 * 作用：ActivityBeginPersenter(欢迎页)
 */
public class ActivityBeginPersenter extends AppDelegate {
    private Context mcontext;
    private Button btjump;
    private TextView txtsecond;
    //定义一个时间变量 初始值3
    private int time = 3;
    private android.os.Handler handler = new android.os.Handler() {
        //重写handler消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //去上面封装一个整型变量初始值23
            //直接time--
            time--;
            //给控件赋值
            txtsecond.setText(time + "s");
            //判断time==0
            if (time == 0) {
//                //停止发送消息
                handler.removeCallbacksAndMessages(null);
//                //用上下文直接跳转
                mcontext.startActivity(new Intent(mcontext, MainActivity.class));
                //本页面强转上下文  结束
                ((ActivityBegin) mcontext).finish();

            } else {
                //继续延时发送消息 去下面发送消息
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_begin;
    }

    @Override
    public void initData() {
        super.initData();
        initwidget();
        //继续延时发送消息 去下面发送消息
        handler.sendEmptyMessageDelayed(1, 1000);
        //点击跳转
        setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止发送消息
                handler.removeCallbacksAndMessages(null);
                mcontext.startActivity(new Intent(mcontext, MainActivity.class));
                Log.d("ShowActivity", "哈哈哈");

                ((ActivityBegin) mcontext).finish();
            }
        }, R.id.begin_bt_jump);
    }

    private void initwidget() {
        btjump = (Button) getView(R.id.begin_bt_jump);
        txtsecond = (TextView) getView(R.id.begin_bt_second);
    }

    @Override
    public void initContext(Context context) {
        super.initContext(context);
        this.mcontext = context;
    }
}
