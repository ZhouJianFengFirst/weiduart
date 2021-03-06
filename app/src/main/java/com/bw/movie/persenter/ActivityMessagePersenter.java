package com.bw.movie.persenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.activitys.ActivityMessage;
import com.bw.movie.activitys.ActivityResetPwd;
import com.bw.movie.activitys.ActivityUpdateEmail;
import com.bw.movie.activitys.ActivityUpdateName;
import com.bw.movie.activitys.ActivityUpdateSex;
import com.bw.movie.entity.UploadBean;
import com.bw.movie.mvp.view.AppDelegate;
import com.bw.movie.utils.Logger;
import com.bw.movie.utils.Pop;
import com.bw.movie.utils.SharedUtil;
import com.bw.movie.utils.SpUtil;
import com.example.xlistviewlib.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * 作者：mafuyan
 * 时间：2018/11/28
 * 作用：ActivityMessagePersenter(我的信息页面)
 */

//继承APPDelegate
public class ActivityMessagePersenter extends AppDelegate implements View.OnClickListener {
    private Context context;
    private RelativeLayout message_rl_head;
    private RelativeLayout message_rl_nickname;
    private RelativeLayout message_rl_sex;
    private RelativeLayout message_rl_date;
    private RelativeLayout message_rl_phone;
    private RelativeLayout message_rl_email;
    private RelativeLayout message_rl_pwd;
    private CircleImageView message_cv_leftreturn;
    private TextView message_tv_exit;
    private LinearLayout chage_pop;
    private String pic = "head.png";
    private Bitmap head;
    private String message1;
    private String status1;
    private String sessionId1;
    private String userId1;
    private String headPic1;
    private String nickName1;
    private String phone1;
    private String birthday1;
    private String lastLoginTime1;
    private String sex1;
    private SimpleDraweeView message_sdv_head;
    private TextView message_tv_name;
    private TextView message_tv_sex;
    private TextView message_tv_date;
    private TextView message_tv_phone;
    private TextView message_tv_email;
    //sd路径
    private static String path = "/sdcard/myHead/";

    @Override
    protected int getLayoutId() {
        //返回本页面布局
        return R.layout.activity_message;
    }

    //重写初始化上下文方法
    @Override
    public void initContext(Context context) {
        //删了super这行提上去上下文
        this.context = context;
    }

    //重写初始化数据方法
    @Override
    public void initData() {
        super.initData();
        //初始化数据方法
        initwidget();

    }

    //初始化控件方法
    private void initwidget() {
        //获取控件强转提上去
        message_rl_head = (RelativeLayout) getView(R.id.message_rl_head);
        message_rl_nickname = (RelativeLayout) getView(R.id.message_rl_nickname);
        message_rl_sex = (RelativeLayout) getView(R.id.message_rl_sex);
        message_rl_date = (RelativeLayout) getView(R.id.message_rl_date);
        message_rl_phone = (RelativeLayout) getView(R.id.message_rl_phone);
        message_rl_email = (RelativeLayout) getView(R.id.message_rl_email);
        message_rl_pwd = (RelativeLayout) getView(R.id.message_rl_pwd);
        message_cv_leftreturn = (CircleImageView) getView(R.id.message_cv_leftreturn);
        message_tv_exit = (TextView) getView(R.id.message_tv_exit);
        chage_pop = (LinearLayout) getView(R.id.chage_pop);
        message_sdv_head = (SimpleDraweeView) getView(R.id.message_sdv_head);
        message_tv_name = (TextView) getView(R.id.message_tv_name);
        message_tv_sex = (TextView) getView(R.id.message_tv_sex);
        message_tv_date = (TextView) getView(R.id.message_tv_date);
        message_tv_phone = (TextView) getView(R.id.message_tv_phone);
        message_tv_email = (TextView) getView(R.id.message_tv_email);
        //点击事件
        message_rl_head.setOnClickListener(this);
        message_rl_nickname.setOnClickListener(this);
        message_rl_sex.setOnClickListener(this);
        message_rl_date.setOnClickListener(this);
        message_rl_phone.setOnClickListener(this);
        message_rl_email.setOnClickListener(this);
        message_rl_pwd.setOnClickListener(this);
        message_cv_leftreturn.setOnClickListener(this);
        message_tv_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //选择点击事件
        switch (view.getId()) {
            case R.id.message_rl_head:
                //吐司切换头像
//                Toast.makeText(context,"切换头像",Toast.LENGTH_SHORT).show();
                //弹出popupwindow方法
                popup();
                break;
            case R.id.message_rl_nickname:
                //吐司修改昵称
//                Toast.makeText(context, "修改昵称", Toast.LENGTH_SHORT).show();
                //带值跳转
                //new 意图
                Intent intent = new Intent(context, ActivityUpdateName.class);
                //往意图里面传值
                intent.putExtra("nickName1", nickName1);
                //启动跳转
                context.startActivity(intent);
                break;
            case R.id.message_rl_sex:
                //吐司修改性别
//                Toast.makeText(context, "修改性别", Toast.LENGTH_SHORT).show();
                //带值跳转
                //new 意图
                Intent intent1 = new Intent(context, ActivityUpdateSex.class);
                //往意图里面传值
                intent1.putExtra("sex1", sex1);
                //启动跳转
                context.startActivity(intent1);
                break;
            case R.id.message_rl_date:
                //吐司修改出生日期
                Toast.makeText(context, "出生日期", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_phone:
                //吐司修改手机号
                Toast.makeText(context, "手机号", Toast.LENGTH_SHORT).show();
                break;
            case R.id.message_rl_email:
                //吐司修改邮箱
//                Toast.makeText(context, "修改邮箱", Toast.LENGTH_SHORT).show();
                //跳转强转上下文
                context.startActivity(new Intent(context, ActivityUpdateEmail.class));
                break;
            case R.id.message_rl_pwd:
                //吐司重置密码
//                Toast.makeText(context,"重置密码",Toast.LENGTH_SHORT).show();
                //跳转强转上下文
                context.startActivity(new Intent(context, ActivityResetPwd.class));
                break;
            case R.id.message_cv_leftreturn:
                //吐司销毁这个页面,返回上一个
//                Toast.makeText(context,"销毁这个页面,返回上一个",Toast.LENGTH_SHORT).show();
                //强转上下文销毁这个页面
                ((ActivityMessage) context).finish();
                break;
            case R.id.message_tv_exit:
                //吐司退出登录
//                Toast.makeText(context, "已经退出登录", Toast.LENGTH_SHORT).show();
//                //销毁本页面
//                ((ActivityMessage) context).finish();
                //退出登录的方法
                backLogin();
                break;

        }
    }

    //退出登录的方法
    private void backLogin() {
        //重新赋值空所有的  登录状态赋值为假
        SharedUtil.put(context, "phones", "");
        SharedUtil.put(context, "sex", "");
        SpUtil.saveData(context, "message", "");
        SpUtil.saveData(context, "status", "");
        SpUtil.saveData(context, "sessionId", "");
        SpUtil.saveData(context, "userId", "");
        SpUtil.saveData(context, "headPic", "");
        SpUtil.saveData(context, "nickName", "");
        SpUtil.saveData(context, "phone", "");
        SpUtil.saveData(context, "birthday", "");
        SpUtil.saveData(context, "id", "");
        SpUtil.saveData(context, "lastLoginTime", "");
        SpUtil.saveData(context, "sex", "");
        SpUtil.saveData(context, "isLogin", false);
        SpUtil.saveData(context, "pwd", "");
        SpUtil.saveData(context, "isRemenber", false);
        SpUtil.saveData(context, "isAuto", false);
        //吐司退出登录
        Toast.makeText(context, "已经退出登录", Toast.LENGTH_SHORT).show();
        //销毁本页面
        ((ActivityMessage) context).finish();
    }

    //弹出popupwindow 方法
    private void popup() {
        //先去创建一个pop的工具类
        //new 调用pop工具类 括号里传上下文和本页面布局整体的id new 接口提示
        new Pop(context, chage_pop, new Pop.OnSelectPictureListener() {
            @Override
            public void onTakePhoto() {
                //new 意图跳转相机 mediastore .相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //意图传值mediastor .ex  ,uri.fromfile括号new文件传获取系统路径传文件名
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), pic)));
                //回调方法跳转相机强转本页面0 回调方法写在Activity 调用方法生成过来
                ((ActivityMessage) context).startActivityForResult(intent, 1);
            }

            @Override
            public void onSelectPicture() {
                //new 意图跳转相册
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                //意图设置dataAnd Type mediastore images.media .ex ,imgae/*通配符
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                //带值跳转1
                ((ActivityMessage) context).startActivityForResult(intent, 0);
            }

            @Override
            public void onCancel() {
                //取消方法不用写
            }
        });
    }

    //相机
    public void onCode0(int resultCode, Intent data) {
        //判断code==RESULT _OK
        if (resultCode == RESULT_OK) {
            //调用裁剪的方法
            cropPhoto(data.getData());
        }
    }

    //相册
    public void onCode1(int resultCode) {
        //判断code==RESULT _OK
        if (resultCode == RESULT_OK) {
            //new 文件
            File file = new File(Environment.getExternalStorageDirectory() + "/head.png");
            //调用裁剪方法传file URI 调用filefromfile
            cropPhoto(Uri.fromFile(file));
        }
    }

    //裁剪回调方法
    public void onCode2(Intent data) {
        Log.i("检查上传头像",data+"extras我进来了");
        //先判断data非空
        if (data != null) {
            //data 获取extras 返回值
            Bundle extras = data.getExtras();
            Log.i("检查上传头像",data+"data"+extras+"extras我");
            //判断等于空直接返回
            if (extras == null) {
                return;
            }
            //extras .获取p传引号data返回值head  提上去Bitmap类型
            head = extras.getParcelable("data");
            Log.i("检查上传头像",head+"data");
            //判断判断head非空
            if (head != null) {
//                //上面封装的固定路径+引号/+pic图片名
                String fileNname = path + "/" + pic;
//                //Bitmap转换uri
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(((ActivityMessage) context).getContentResolver(), BitmapFactory.decodeFile(fileNname), null, null));
//                //给头像设置imagebitmap 传head
//                message_sdv_head.setImageURI(uri);
                // 调用储存sd卡的方法 传head
                setPicToView(head);
                //上传头像方法
                uploadImage(fileNname);
                //重新保存头像的方法
//                setIcon();
            }
        }
    }

    //上传头像
    private void uploadImage(String path) {
        File file = new File(path);
        Log.i("检查上传头像",userId1+"oo"+sessionId1+"pp"+path);
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userId1);
        map.put("sessionId", sessionId1);
        upLoad(file, 0, map);
    }

    @Override
    public void successString(String data, int type) {
        super.successString(data, type);
        switch (type) {
            case 0:
                Logger.i("修改头像返回", "哈哈" + data);
                UploadBean uploadBean = new Gson().fromJson(data, UploadBean.class);
                if (uploadBean.getMessage().equals("上传成功")) {
                    toast(context, "上传成功");
                } else if (uploadBean.getMessage().equals("请先登录")) {
                    toast(context, "登录过期,请重新登录~");
                    return;
                } else if ("网络异常,请联系管理员".equals(uploadBean.getMessage())) {
                    //吐司网络异常，请联系管理员
                    toast(context, "网络异常,请联系管理员");
                    //吐司完直接返回 不往下执行
                    return;
                }
                //重新保存头像
                SpUtil.saveData(context, "headPic", uploadBean.getHeadPath());
                //重新给控件赋值
                message_sdv_head.setImageURI(Uri.parse(uploadBean.getHeadPath()));
                break;
        }
    }

    // 调用系统的裁剪
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 127);
        intent.putExtra("outputY", 127);
        intent.putExtra("scale", true);
        intent.putExtra("noFaceDetection", false);//不启用人脸识别
        intent.putExtra("return-data", true);
        ((ActivityMessage) context).startActivityForResult(intent, 2);
    }

    //把头像缓存到sd卡的方法
    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/head.png";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取到的值
    public void setData(String message1, String status1, String sessionId1, String userId1, String headPic1, String nickName1, String phone1, String birthday1, String lastLoginTime1, String sex1) {
        //this.名称=名称提上去
        this.message1 = message1;
        this.status1 = status1;
        this.sessionId1 = sessionId1;
        this.userId1 = userId1;
        this.headPic1 = headPic1;
        this.nickName1 = nickName1;
        this.phone1 = phone1;
        this.birthday1 = birthday1;
        this.lastLoginTime1 = lastLoginTime1;
        this.sex1 = sex1;
        Logger.i("头像", headPic1);
        //给控件重新赋值
        message_tv_name.setText(nickName1);
        //判断=1=2
        if ("1".equals(sex1)) {
            message_tv_sex.setText("男");
        } else if ("2".equals(sex1)) {
            message_tv_sex.setText("女");
        }
        //出生日期
        if ("暂未设定".equals(birthday1)) {
            message_tv_date.setText(birthday1);
        } else {
            message_tv_date.setText(DateUtils.format(Long.parseLong(birthday1), "yyyy-MM-dd"));
        }
        message_tv_phone.setText(phone1);
        message_sdv_head.setImageURI(Uri.parse(headPic1));
    }
}