package com.weidukeji.agriculture.fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;


public class DiscoveryFragment extends BaseFragment implements View.OnClickListener {


    @Override
    protected void findView(View showView) {
        showView.findViewById(R.id.layout_today_report).setOnClickListener(this);
        showView.findViewById(R.id.layout_nearby_chat).setOnClickListener(this);
        showView.findViewById(R.id.layout_nearby_discount).setOnClickListener(this);
    }

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View developView = inflater.inflate(R.layout.fragment_discovery, container, false);
        return developView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_today_report:

                break;
            case R.id.layout_nearby_chat:

                break;
            case R.id.layout_nearby_discount:

                break;
        }
    }
}
