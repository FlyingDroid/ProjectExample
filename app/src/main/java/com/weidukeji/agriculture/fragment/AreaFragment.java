package com.weidukeji.agriculture.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;

public class AreaFragment extends BaseFragment {


    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
       View areaView= inflater.inflate(R.layout.fragment_area,container,false);
        return areaView;
    }

    @Override
    protected void findView(View showView) {

    }

    @Override
    public void initData() {

    }


}
