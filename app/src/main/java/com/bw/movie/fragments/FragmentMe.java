package com.bw.movie.fragments;

import com.bw.movie.mvp.persenter.BaseFragment;
import com.bw.movie.persenter.FragmentMePresenter;
import com.bw.movie.utils.SpUtil;

/**
 * 作者：mafuyan
 * 时间：2018/11/30
 * 作用：FragmentMe(我的页面)
 * */
public    class FragmentMe extends BaseFragment<FragmentMePresenter>{
    @Override
    public Class<FragmentMePresenter> getDelegateClass() {
        return FragmentMePresenter.class;
    }
    //重写生命周期onResume 聚焦方法
    @Override
    public void onResume() {
        super.onResume();
//        //获取sp里面的数据userId sessionId 调用工具类强转自己需要的类型 提上去调用
        String message1 = (String) SpUtil.getInserter(getActivity()).getSpData("message", "");
        String status1 = (String) SpUtil.getInserter(getActivity()).getSpData("status", "");
        String sessionId1 = (String) SpUtil.getInserter(getActivity()).getSpData("sessionId()", "");
        String userId1 = (String) SpUtil.getInserter(getActivity()).getSpData("userId", "");
        String headPic1 = (String) SpUtil.getInserter(getActivity()).getSpData("headPic", "");
        String nickName1 = (String) SpUtil.getInserter(getActivity()).getSpData("nickName", "");
        String phone1 = (String) SpUtil.getInserter(getActivity()).getSpData("phone", "");
        String birthday1 = (String) SpUtil.getInserter(getActivity()).getSpData("birthday", "");
        String id1 = (String) SpUtil.getInserter(getActivity()).getSpData("id", "");
        String lastLoginTime1 = (String) SpUtil.getInserter(getActivity()).getSpData("lastLoginTime", "");
        String sex1 = (String) SpUtil.getInserter(getActivity()).getSpData("sex", "");
        //调用方法传获取到的值
        delegate.setData(message1,status1,sessionId1,userId1,headPic1,nickName1,phone1,birthday1,id1,lastLoginTime1,sex1);
    }
}