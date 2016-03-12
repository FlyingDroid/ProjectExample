package com.weidukeji.agriculture.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weidukeji.agriculture.base.BasePager;

/**
 * Created by admin on 2015/1/6.
 */
public class NewsHotPager extends BasePager {
    public NewsHotPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(context);
        textView.setText("列表页面");
        textView.setTextColor(context.getResources().getColor(android.R.color.black));
        return textView;
    }

    @Override
    public void initData() {
        Toast.makeText(context, "列表页面加载数据", Toast.LENGTH_LONG).show();
    }
}
