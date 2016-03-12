package com.weidukeji.agriculture.fragment;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.activity.MainActivity3;
import com.weidukeji.agriculture.base.BaseFragment;

/**
 * Created by admin on 2015/1/4.
 */
public class ServiceFragment extends BaseFragment {

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
       View serviceView = inflater.inflate(R.layout.fragment_service,container,false);
        return serviceView;
    }
    @Override
    protected void findView(View showView) {

    }

    @Override
    public void initData() {

    }

}
