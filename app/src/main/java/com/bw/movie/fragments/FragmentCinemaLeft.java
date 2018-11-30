package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentCinemaLeftPresenter;
import com.bw.movie.persenter.FragmentCinemaPresenter;
import com.bw.movie.utils.SpUtil;

/**
*作者：gaojiabao
*时间：2018/11/28 18:39
*作用：影院详情
*/
public class FragmentCinemaLeft extends BaseFragment<FragmentCinemaLeftPresenter> {
    @Override
    public Class<FragmentCinemaLeftPresenter> getDelegateClass() {
        return FragmentCinemaLeftPresenter.class;
    }
    //重写生命周期onResume 聚焦方法
    @Override
    public void onResume() {
        super.onResume();
        Boolean islogin = (Boolean) SpUtil.getSpData(getActivity(),"isLogin",false);
        if (islogin){
            //获取sp里面的数据userId sessionId 调用工具类强转自己需要的类型 提上去调用
            String message1 = (String) SpUtil.getSpData(getActivity(),"message", "");
            String status1 = (String) SpUtil.getSpData(getActivity(),"status", "");
            String sessionId1 = (String) SpUtil.getSpData(getActivity(),"sessionId", "");
            String userId1 = (String) SpUtil.getSpData(getActivity(),"userId", "");
            String headPic1 = (String)SpUtil.getSpData(getActivity(),"headPic", "");
            String nickName1 = (String) SpUtil.getSpData(getActivity(),"nickName", "");
            String phone1 = (String) SpUtil.getSpData(getActivity(),"phone", "");
            String birthday1 = (String) SpUtil.getSpData(getActivity(),"birthday", "");
            String id1 = (String) SpUtil.getSpData(getActivity(),"id", "");
            String lastLoginTime1 = (String) SpUtil.getSpData(getActivity(),"lastLoginTime", "");
            String sex1 = (String) SpUtil.getSpData(getActivity(),"sex", "");
            //调用方法传获取到的值
            delegate.setData(message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1);
        }
    }

}