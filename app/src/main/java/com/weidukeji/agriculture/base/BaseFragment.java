package com.weidukeji.agriculture.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2014/12/29.
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
    public boolean has_success_data =false;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View showView =showView(container,inflater);
        findView(showView);
        return showView;
    }

    protected abstract void findView(View showView);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 在fragment中要展示的View,作为 oncreateView()的返回值。
     * @param container
     * @param inflater
     * @return
     */
    public abstract View showView(ViewGroup container, LayoutInflater inflater) ;
    /**
     * 在onActivityCreated()中,onCreateView()之后被调用
     * 在该方法中进行页面数据的初始化
     */
    public abstract void initData();


}
