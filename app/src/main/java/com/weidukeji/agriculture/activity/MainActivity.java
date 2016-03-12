package com.weidukeji.agriculture.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseActivity;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.engine.GdMapLocationListener;
import com.weidukeji.agriculture.fragment.MsgFragment;
import com.weidukeji.agriculture.fragment.NearbyFragment;
import com.weidukeji.agriculture.fragment.NewsFragment;
import com.weidukeji.agriculture.fragment.UserFragment;
import com.weidukeji.agriculture.widget.drawermenu.DragLayout;


public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private DragLayout dragLayout;
    private int index=0;
    private int currentItem =R.id.radio_btn_news;
    private BaseFragment fragment=null;
    private ListView lv_drag_menu;
    private RadioGroup radioGroup_main;
    @Override
    public void initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        dragLayout = (DragLayout) findViewById(R.id.dl);
        dragLayout.setDragListener(new DragLayout.DragListener() {
            @Override
            public void onOpen() {
            }

            @Override
            public void onClose() {

            }

            @Override
            public void onDrag(float percent) {

            }
        });

        lv_drag_menu = (ListView) findViewById(R.id.lv_drag_menu);
        lv_drag_menu.setOnItemClickListener(this);
        String[] dragMenus = getResources().getStringArray(R.array.lv_drag_menu);
        lv_drag_menu.setAdapter(new MenuAdapter(dragMenus));
        findViewById(R.id.btn_main_menu).setOnClickListener(this);
        final Button btn_location = (Button) findViewById(R.id.btn_main_location);
        btn_location.setOnClickListener(this);
        final TextView tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        final FrameLayout mainContent = (FrameLayout) findViewById(R.id.layout_main_content);
        radioGroup_main = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                currentItem=checkedId;
                switch (checkedId){
                    case R.id.radio_btn_news:
                        index=0;
                        tv_main_title.setText(getString(R.string.nearby));
                        break;
                    case R.id.radio_btn_rss:
                        index=1;
                        tv_main_title.setText(getString(R.string.discovery));
                        break;
                    case R.id.radio_btn_message:
                        index=2;
                        tv_main_title.setText(getString(R.string.message));
                        break;
                    case R.id.radio_btn_user:
                        index=3;
                        tv_main_title.setText(getString(R.string.user));
                        break;
                }
                if(currentItem==R.id.radio_btn_news||currentItem==R.id.radio_btn_rss){
                    btn_location.setVisibility(View.VISIBLE);
                }else{
                    btn_location.setVisibility(View.GONE);
                }
                BaseFragment currentFrag = (BaseFragment) fragmentPagerAdapter.instantiateItem(mainContent,index);
                fragmentPagerAdapter.setPrimaryItem(mainContent,0,currentFrag);
                fragmentPagerAdapter.finishUpdate(mainContent);
            }
        });
        radioGroup_main.check(currentItem);

    }
    FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    fragment = new NewsFragment();
                    break;
                case 1:
                    fragment =new NearbyFragment();
                    break;
                case 2:
                    fragment = new MsgFragment();
                    break;
                case 3:
                    fragment =new UserFragment();
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_menu:
                dragLayout.open(true);
                break;
            case R.id.btn_main_location:

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent =null;
        switch (position){

            case 0://订阅
                intent = new Intent(this,RssActivity.class);
                break;
            case 1://我的地盘
                intent = new Intent(this,AreaActivity.class);
                break;
            case 2://知识社区
                intent = new Intent(this,WisdomActivity.class);
                break;
            case 3://服务频道
                intent = new Intent(this,ServiceActivity.class);
                break;
        }
        if(intent!=null)
            startActivity(intent);
    }

    private class MenuAdapter extends BaseAdapter{
        private String[] items;
        public MenuAdapter(String[] dragMenus) {
            this.items =dragMenus;
        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null){
                convertView =View.inflate(MainActivity.this,R.layout.view_item_drag_menu,null);
            }
            TextView tv_menu_item = (TextView) convertView.findViewById(R.id.tv_drag_menu_item);
            ImageView img_menu = (ImageView) convertView.findViewById(R.id.img_drag_menu);
            switch (position){

                case 0://订阅
                    img_menu.setBackgroundResource(R.drawable.selector_menu_tianqi);
                    break;
                case 1://我的地盘
                    img_menu.setBackgroundResource(R.drawable.selector_menu_zhci);
                    break;
                case 2://知识社区
                    img_menu.setBackgroundResource(R.drawable.selector_menu_jishu);
                    break;
                case 3://服务频道
                    img_menu.setBackgroundResource(R.drawable.selector_menu_jiage);
                    break;
            }
            tv_menu_item.setText(items[position]);
            return convertView;
        }
    }
    @Override
    public void initFragment(Bundle Sav) {
        initGdMap();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private LocationManagerProxy mLocationManagerProxy;
    /**
     * 初始化定位
     */
    private void initGdMap() {
        mLocationManagerProxy = LocationManagerProxy.getInstance(this);
        GdMapLocationListener gdMapLocationListener =GdMapLocationListener.getWBaidDuLocationListener();
        mLocationManagerProxy.requestLocationData(
                LocationProviderProxy.AMapNetwork, 1000*60,100,gdMapLocationListener);
        mLocationManagerProxy.setGpsEnable(false);
    }
}
