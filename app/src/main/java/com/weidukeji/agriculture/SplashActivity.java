package com.weidukeji.agriculture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.weidukeji.agriculture.activity.MainActivity4;
import com.weidukeji.agriculture.engine.GdMapLocationListener;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(SplashActivity.this, MainActivity4.class));
                finish();
            }
        }.sendEmptyMessageDelayed(100, 3000);

    }


}
