package com.weidukeji.quality.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.quality.R;

public class QualityFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View qualityView = inflater.inflate(R.layout.fragment_quality, container, false);
        return qualityView;
    }


    @Override
    protected void initData() {

    }
}
