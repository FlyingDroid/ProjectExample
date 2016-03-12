package com.weidukeji.agriculture.activity;

import android.os.Bundle;
import android.view.Window;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.RssFragment;
import com.weidukeji.agriculture.fragment.ServiceFragment;

public class ServiceActivity extends BaseActivity {
    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_container);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        if(null==savedInstanceState){
            ServiceFragment serviceFragment = new ServiceFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container,serviceFragment).commit();
        }
    }

}
