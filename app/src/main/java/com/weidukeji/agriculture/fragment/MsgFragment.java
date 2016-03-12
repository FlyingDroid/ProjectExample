package com.weidukeji.agriculture.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weidukeji.agriculture.R;
import com.weidukeji.agriculture.base.BaseFragment;
import com.weidukeji.agriculture.utils.ScreenUtils;
import com.weidukeji.agriculture.widget.swipemenulistview.SwipeMenu;
import com.weidukeji.agriculture.widget.swipemenulistview.SwipeMenuCreator;
import com.weidukeji.agriculture.widget.swipemenulistview.SwipeMenuItem;
import com.weidukeji.agriculture.widget.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;


public class MsgFragment extends BaseFragment {

    private View msgView;
    private SwipeMenuListView swipeMenuListView;
    @Override
    public View showView(ViewGroup container, LayoutInflater inflater) {
        msgView = inflater.inflate(R.layout.fragment_home_msg, container, false);
        return msgView;
    }
    @Override
    protected void findView(View showView){
        swipeMenuListView = (SwipeMenuListView) showView.findViewById(R.id.lv_msg_swipe);
        SwipeMenuCreator menuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem menuOpenItem = new SwipeMenuItem(context);
                menuOpenItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
               menuOpenItem.setWidth(ScreenUtils.dpToPxInt(context,90));
                menuOpenItem.setTitle("打开");
                menuOpenItem.setTitleSize(18);
                menuOpenItem.setTitleColor(Color.BLUE);
                menu.addMenuItem(menuOpenItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        context);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(ScreenUtils.dpToPxInt(context,90));
                deleteItem.setIcon(android.R.drawable.ic_menu_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView.setMenuCreator(menuCreator);
    }

    @Override
    public void initData() {
        ArrayList<String> strs = new ArrayList<>();
        for(int i=0;i<20;i++){
            strs.add("这是推送的消息"+i);
        }
        MsgAdapter msgAdapter =new MsgAdapter(strs);
        swipeMenuListView.setAdapter(msgAdapter);
    }

    private class MsgAdapter extends BaseAdapter {
        private ArrayList<String> strings;
        public MsgAdapter(ArrayList<String> strs) {
            this.strings =strs;
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView= View.inflate(context, R.layout.view_item_msg, null);
            }
            TextView tv_msg_item = (TextView) convertView.findViewById(R.id.tv_msg_item);
            tv_msg_item.setText(strings.get(position));
            return convertView;
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
