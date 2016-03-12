package com.weidukeji.agriculture.activity;

import android.os.Bundle;
import android.view.Window;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.RssFragment;

public class RssActivity extends BaseActivity {

    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_container);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        if(null==savedInstanceState){
            RssFragment rssFragment =new RssFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, rssFragment).commit();
        }
    }

}
