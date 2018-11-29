package com.bw.movie.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bw.movie.R;


public class Pop implements View.OnClickListener{

    private Context context = null;
        private PopupWindow popupWindow = null;
        private OnSelectPictureListener listener = null;

        public Pop(Context context, View popview, OnSelectPictureListener listener) {
            this.context = context;
            this.listener = listener;
            View view = LayoutInflater.from(context).inflate(R.layout.popwindow_layout, null);
            initView(view, popview);
        }

        private void initView(View view, View popview) {
            popupWindow = new PopupWindow(popview,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            popupWindow.setContentView(view);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popupWindow.showAtLocation(popview, Gravity.BOTTOM, 0, 0);//show()
            view.findViewById(R.id.popup_select_take_photo).setOnClickListener(this);
            view.findViewById(R.id.popup_select_take_picture).setOnClickListener(this);
            view.findViewById(R.id.popup_select_take_cancel).setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int i = view.getId();
            if (i == R.id.popup_select_take_photo) {
                listener.onTakePhoto();

            } else if (i == R.id.popup_select_take_picture) {
                listener.onSelectPicture();

            } else if (i == R.id.popup_select_take_cancel) {
                listener.onCancel();
            }
            //点击完了之后隐藏
            popupWindow.dismiss();
        }


        //点击接口
        public interface OnSelectPictureListener {
            /**
             * 拍摄
             */
            void onTakePhoto();

            /**
             * 从相册选择
             */
            void onSelectPicture();

            /**
             * 取消
             */
            void onCancel();
        }

}
