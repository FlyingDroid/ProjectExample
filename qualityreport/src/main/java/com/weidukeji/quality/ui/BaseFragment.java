package com.weidukeji.quality.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by admin on 2015/2/4.
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 在onActivityCreated()中,onCreateView()之后被调用
     * 在该方法中进行页面数据的初始化
     */
    protected abstract void initData();
}
