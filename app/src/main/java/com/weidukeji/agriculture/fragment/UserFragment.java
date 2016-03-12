package com.weidukeji.agriculture.fragment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.base.WdBaseAdapter;
import com.weidukeji.agriculture.widget.PullToZoomListView;

import java.util.ArrayList;
import java.util.List;


public class UserFragment extends BaseFragment {

    private PullToZoomListView pzLv;

    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        View userView = inflater.inflate(R.layout.fragment_home_user, container, false);
        pzLv = (PullToZoomListView) userView.findViewById(R.id.lv_user_ptz);
        ImageView headBgImage = pzLv.getHeaderView();
        headBgImage.setImageResource(R.drawable.user_bg);
        headBgImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        View headerContainerView = View.inflate(context, R.layout.view_user_header, null);
        pzLv.getHeaderContainerView().addView(headerContainerView);
        return userView;
    }

    @Override
    protected void findView(View showView) {

    }

    @Override
    public void initData() {
        ArrayList<UserItem> userItems = new ArrayList<>();
        userItems.add(new UserItem(R.drawable.selector_menu_jishu, "我的好友"));
        userItems.add(new UserItem(R.drawable.selector_menu_jishu, "我的收藏"));
        userItems.add(new UserItem(R.drawable.selector_menu_jishu, "关注的微度"));
        userItems.add(new UserItem(R.drawable.selector_menu_jishu, "发布的事情"));
        UserItemAdapter userItemAdapter = new UserItemAdapter(context, userItems);
        pzLv.setAdapter(userItemAdapter);
        pzLv.setOnPullScrollRefreshListener(new PullToZoomListView.OnPullScrollRefreshListener() {
            @Override
            public void onStartPull() {

            }
        });
        pzLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    private class UserItemAdapter extends WdBaseAdapter<UserItem> {
        public UserItemAdapter(Context context, List<UserItem> lists) {
            super(context, lists);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.view_item_user, null);
            }
            ImageView imgUserItem = (ImageView) convertView.findViewById(R.id.img_user_item);
            UserItem userItem = lists.get(position);
            imgUserItem.setImageResource(userItem.drawable);
            TextView tvUserItem = (TextView) convertView.findViewById(R.id.tv_user_item);
            tvUserItem.setText(userItem.itemDetail);
            return convertView;
        }
    }

    private class UserItem {
        private int drawable;
        private String itemDetail;

        public UserItem(int drawable, String itemDetail) {
            this.drawable = drawable;
            this.itemDetail = itemDetail;
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
