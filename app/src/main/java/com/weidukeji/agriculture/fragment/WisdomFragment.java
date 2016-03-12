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
public class WisdomFragment extends BaseFragment {


    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View wisdomView =inflater.inflate(R.layout.fragment_wisdom,container,false);
        return wisdomView;
    }
    @Override
    protected void findView(View showView) {

    }
    @Override
    public void initData() {

    }

}
