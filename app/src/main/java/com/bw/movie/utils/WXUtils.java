package com.bw.movie.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.bw.movie.activitys.MainActivity;
import com.bw.movie.entity.WxUserBean;
import com.bw.movie.net.BaseObserver;
import com.bw.movie.net.HttpHelper;
import com.bw.movie.wxapi.WXEntryActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public class WXUtils {
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void getWXUserInfo(String code, Context mcontext) {

        Map<String, String> hmap = new HashMap<>();
        hmap.put("Content-Type", "application/x-www-form-urlencoded");
        Map<String, String> fmap = new HashMap<>();
        fmap.put("code", code);
        HttpHelper.getInstens().headPost("/movieApi/user/v1/weChatBindingLogin", hmap, fmap, new BaseObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {

                try {
                    String string = responseBody.string();

                    WxUserBean loginBean = new Gson().fromJson(string, WxUserBean.class);
                    SpUtil.saveData(mcontext, "sex", loginBean.getResult().getUserInfo().getSex() + "");
                    SpUtil.saveData(mcontext, "message", loginBean.getMessage());
                    SpUtil.saveData(mcontext, "status", loginBean.getStatus());
                    SpUtil.saveData(mcontext, "sessionId", loginBean.getResult().getSessionId());
                    SpUtil.saveData(mcontext, "userId", loginBean.getResult().getUserId() + "");
                    SpUtil.saveData(mcontext, "headPic", loginBean.getResult().getUserInfo().getHeadPic());
                    SpUtil.saveData(mcontext, "nickName", loginBean.getResult().getUserInfo().getNickName());
                    SpUtil.saveData(mcontext, "phone", "暂无手机号");
                    SpUtil.saveData(mcontext, "birthday", "暂未设定");
                    SpUtil.saveData(mcontext, "lastLoginTime", loginBean.getResult().getUserInfo().getLastLoginTime() + "");
                    SpUtil.saveData(mcontext, "isLogin", true);
                    SpUtil.saveData(mcontext, "isAuto", true);
                    mcontext.startActivity(new Intent(mcontext, MainActivity.class));
                    ((WXEntryActivity) mcontext).finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(mcontext, "请求错误", Toast.LENGTH_LONG).show();
            }
        });
    }

}
