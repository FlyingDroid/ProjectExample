package com.weidukeji.agriculture.fragment;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.activity.MainActivity3;
import com.weidukeji.agriculture.base.BaseFragment;


public class RssFragment extends BaseFragment {




    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View rssView =inflater.inflate(R.layout.fragment_rss,container,false);
        return rssView;
    }
    @Override
    protected void findView(View showView) {

    }
    @Override
    public void initData() {

    }

}

