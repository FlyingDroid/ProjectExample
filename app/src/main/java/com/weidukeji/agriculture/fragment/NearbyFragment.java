package com.weidukeji.agriculture.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.activity.InforDetailActivity;
import com.weidukeji.agriculture.activity.PublishActivity;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.base.WdBaseAdapter;
import com.weidukeji.agriculture.engine.GdMapLocationListener;
import com.weidukeji.agriculture.utils.AndLogger;
import com.weidukeji.agriculture.widget.floatbutton.FloatingActionButton;
import com.weidukeji.agriculture.widget.floatbutton.ShowHideOnScroll;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class NearbyFragment extends BaseFragment {
    private ListView lv_nearby;
    private PtrFrameLayout ptrFrameLayout;
    private View nearbyView;

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        nearbyView = inflater.inflate(R.layout.fragment_home_nearby, container, false);
        return nearbyView;
    }

    @Override
    protected void findView(View showView) {
        PtrFrameLayout.DEBUG = false;
        ptrFrameLayout = (PtrFrameLayout) showView.findViewById(R.id.material_style_ptr_frame);
        MaterialHeader header = new MaterialHeader(context);
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(10), 0, PtrLocalDisplay.dp2px(10));
        header.setPtrFrameLayout(ptrFrameLayout);

        ptrFrameLayout.setLoadingMinTime(1000);// loading will last at least for so long
        ptrFrameLayout.setDurationToCloseHeader(1500);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrameLayout.autoRefresh(false);
            }
        }, 100);

        ptrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                long delay = (long) (1000 + Math.random() * 2000);
                delay = Math.max(0, delay);
                delay = 0;
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, delay);
            }
        });
        lv_nearby = (ListView) showView.findViewById(R.id.lv_nearby);
        FloatingActionButton fabBtn = (FloatingActionButton) showView.findViewById(R.id.btn_fab);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndLogger.zhshengLog().d("发布消息");
                startActivity(new Intent(context, PublishActivity.class));
            }
        });
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            imgs.add("我是记者" + i);
        }
        lv_nearby.setAdapter(new NearbyAdapter(context, imgs));
        lv_nearby.setOnTouchListener(new ShowHideOnScroll(fabBtn));
        lv_nearby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AndLogger.zhshengLog().d("查看详情");
                Intent intent = new Intent(context, InforDetailActivity.class);
                startActivity(intent);
            }
        });
        lv_nearby.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (view.getLastVisiblePosition() == view.getCount() - 1) {
                            AndLogger.zhshengLog().d("滚动到底部");
                        }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


    }


    @Override
    public void initData() {
        GdMapLocationListener wBaidDuLocationListener = GdMapLocationListener.getWBaidDuLocationListener();
        wBaidDuLocationListener.registerWdLocationListener(new GdMapLocationListener.WdLocationListener() {
            @Override
            public void getLocation(AMapLocation aMapLocation) {
                AndLogger.zhshengLog().d("附近的位置:" + aMapLocation.getDistrict());
            }
        });

    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }

    private class NearbyAdapter extends WdBaseAdapter<String> {
        public NearbyAdapter(Context context, List<String> lists) {
            super(context, lists);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.view_item_nearby, null);
            }

            TextView tv_nearby = (TextView) convertView.findViewById(R.id.tv_userName);
            tv_nearby.setText(lists.get(position));
            return convertView;
        }
    }
}
