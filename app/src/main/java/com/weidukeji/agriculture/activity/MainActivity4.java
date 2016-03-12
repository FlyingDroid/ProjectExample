package com.weidukeji.agriculture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.engine.GdMapLocationListener;
import com.weidukeji.agriculture.fragment.DiscoveryFragment;
import com.weidukeji.agriculture.fragment.MsgFragment;
import com.weidukeji.agriculture.fragment.NearbyFragment;
import com.weidukeji.agriculture.fragment.UserFragment;
import com.weidukeji.agriculture.utils.AndLogger;

import java.lang.reflect.Method;

public class MainActivity4 extends BaseActivity {
    private int currentItem = R.id.radio_btn_news;
    private BaseFragment fragment = null;
    private RadioGroup radioGroup_main;
    private int index = 0;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main_activity4);
    }

    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    fragment = new NearbyFragment();
                    break;
                case 1:
                    fragment = new DiscoveryFragment();
                    break;
                case 2:
                    fragment = new MsgFragment();
                    break;
                case 3:
                    fragment = new UserFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    @Override
    public void initFragment(Bundle savedInstanceState) {
        actionBar.setDisplayShowHomeEnabled(true);//显示home图片
        actionBar.setIcon(R.drawable.ic_launcher);
        final FrameLayout mainContent = (FrameLayout) findViewById(R.id.layout_main_content);
        RadioGroup radioGroup_main = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentItem = checkedId;
                switch (currentItem) {
                    case R.id.radio_btn_news:
                        index = 0;
                        break;
                    case R.id.radio_btn_rss:
                        index = 1;
                        break;
                    case R.id.radio_btn_message:
                        index = 2;
                        break;
                    case R.id.radio_btn_user:
                        index = 3;
                        break;
                }
                BaseFragment currentFrag = (BaseFragment) fragmentPagerAdapter.instantiateItem(mainContent, index);
                fragmentPagerAdapter.setPrimaryItem(mainContent, 0, currentFrag);
                fragmentPagerAdapter.finishUpdate(mainContent);
            }
        });
        radioGroup_main.check(currentItem);
        initLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
        return true;
    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        setOverflowIconVisible(featureId, menu);
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 利用反射让隐藏在Overflow中的MenuItem显示Icon图标
     *
     * @param featureId
     * @param menu      onMenuOpened方法中调用
     */
    public static void setOverflowIconVisible(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_location:
                AndLogger.zhshengLog().d("选择定位");
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.menu_examine:
                AndLogger.zhshengLog().d("审查");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private LocationManagerProxy locationManagerProxy;
    private GdMapLocationListener wBaidDuLocationListener;

    private void initLocation() {
        locationManagerProxy = LocationManagerProxy.getInstance(this);
        wBaidDuLocationListener = GdMapLocationListener.getWBaidDuLocationListener();
        locationManagerProxy.requestLocationData(LocationProviderProxy.AMapNetwork, 5 * 1000, 15, wBaidDuLocationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }

    private void stopLocation() {
        if (locationManagerProxy != null) {
            locationManagerProxy.removeUpdates(wBaidDuLocationListener);
            locationManagerProxy.destory();
        }
        locationManagerProxy = null;
    }


}
