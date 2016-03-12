package com.weidukeji.quality.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.quality.R;

public class RubbishFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rubbishView = inflater.inflate(R.layout.fragment_rubbish, container, false);
        return rubbishView;
    }


    @Override
    protected void initData() {

    }
}
