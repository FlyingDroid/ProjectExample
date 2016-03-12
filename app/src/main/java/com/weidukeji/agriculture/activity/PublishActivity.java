package com.weidukeji.agriculture.activity;

import android.os.Bundle;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.PublishFragment;

public class PublishActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_container);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        actionBar.setDisplayHomeAsUpEnabled(true);//显示左边的小箭头
        actionBar.setDisplayShowHomeEnabled(false);//actionBar左侧图标是否显示
        getSupportFragmentManager().beginTransaction().add(R.id.container, PublishFragment.newInstance(null, null)).commit();
    }
}
