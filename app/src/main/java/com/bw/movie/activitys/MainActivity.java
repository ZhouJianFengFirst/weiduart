package com.bw.movie.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.bw.movie.R;
import com.bw.movie.mvp.persenter.BaseActivity;
import com.bw.movie.persenter.ActivityMainPersenter;

/**
 * 作者：zhoujianfeng
 * 时间：2018/11/27
 * 作用：MainActivity
 */
public class MainActivity extends BaseActivity<ActivityMainPersenter> {

    @Override
    public Class<ActivityMainPersenter> getDelegateClass() {
        return ActivityMainPersenter.class;
    }

    @Override
    protected void onDestroy() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("下单");
        alertDialog.setIcon(R.mipmap.place);
        alertDialog.setMessage("是否下单");
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "是否退出应用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//关闭对话框
            }
        });
        alertDialog.show();//显示对话框
        super.onDestroy();
    }
}