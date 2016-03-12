package com.weidukeji.agriculture.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.weidukeji.agriculture.R;


public abstract class BaseActivity extends ActionBarActivity {
    protected ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        actionBar = getSupportActionBar();
       /* if(actionBar!=null)
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_solid));*/
        initFragment(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
    }

    public void initView() {
        setContentView(R.layout.activity_container);
    }

    public abstract void initFragment(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

}
