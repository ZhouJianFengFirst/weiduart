package com.bw.movie.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

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

   /* *//**
     * 获取微信登录，用户授权后的个人信息
     *
     * @param access_token
     * @param openid
     * @param unionid
     *//*
    private void getWXUserInfo(final String access_token, final String openid, final String unionid) {
        Map<String, Object> params = new HashMap();
        params.put("access_token", access_token);
        params.put("openid", openid);
        //https://www.jianshu.com/p/57128969e7eb
        //https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        HttpHelper.getInstens().doGet();
        HttpHelper.getWXUserInfoBean(URLConstant.URL_WX_BASE, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WXUserInfoBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "getWXUserInfo:--------> onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.i(TAG, "getWXUserInfo:--------> onError" + throwable.getMessage());
                    }

                    @Override
                    public void onNext(WXUserInfoBean wxUserInfoBean) {
                        Log.i(T,,AG, "getWXUserInfo:--------> onNext");
                        String country = wxUserInfoBean.getCountry(); //国家
                        String province = wxUserInfoBean.getProvince(); //省
                        String city = wxUserInfoBean.getCity(); //市
                        String nickname = wxUserInfoBean.getNickname(); //用户名
                        int sex = wxUserInfoBean.getSex(); //性别
                        String headimgurl = wxUserInfoBean.getHeadimgurl(); //头像url
                        Log.i(TAG, "country:-------->" + country);
                        Log.i(TAG, "province:-------->" + province);
                        Log.i(TAG, "city:-------->" + city);
                        Log.i(TAG, "nickname:-------->" + nickname);
                        Log.i(TAG, "sex:-------->" + sex);
                        Log.i(TAG, "headimgurl:-------->" + headimgurl);
                    }
                });
    }*/

}
