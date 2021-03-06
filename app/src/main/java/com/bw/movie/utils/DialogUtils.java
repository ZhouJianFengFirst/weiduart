package com.bw.movie.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;

/**
 * author:周建峰
 * date:2018/10/21
 */
public class DialogUtils extends Dialog {

    public DialogUtils(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public DialogUtils(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected DialogUtils(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_nonetwork, null);
        ImageView imgnetwork =(ImageView) view.findViewById(R.id.film_img_nonetwork);
        TextView txtnetwork =(TextView) view.findViewById(R.id.film_txt_nonetwork);
        setContentView(view);
    }
    /*private void setNoNetWork(Context context) {
        View view = View.inflate(context, R.layout.layout_nonetwork, null);
        setContentView(view);
    }*/
}
