package com.weidukeji.agriculture.activity;

import android.os.Bundle;
import android.view.Window;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.fragment.WisdomFragment;

public class WisdomActivity extends BaseActivity {
    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_container);
    }

    @Override
    public void initFragment(Bundle savedInstanceState) {
        if (null == savedInstanceState) {
            WisdomFragment wisdomFragment = new WisdomFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, wisdomFragment).commit();
        }
    }

}
