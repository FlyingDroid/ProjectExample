package com.weidukeji.agriculture.base;

import android.content.Context;
import android.view.View;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by zhsheng on 2015/1/5.
 */
public abstract class BasePager {
    public Context context;
    public View view;
    public boolean has_success_data =false;
    public BasePager(Context context) {
        this.context = context;
        view = initView();
    }

    public View getRootView() {
        return view;
    }

    public abstract View initView();

    public abstract void initData();

    public void loadData(HttpRequest.HttpMethod httpMethod, String url, RequestParams requestParams, RequestCallBack<String> requestCallBack) {
        HttpUtils httpUtils = new HttpUtils();
        if (requestParams == null) {
            requestParams = new RequestParams();
        }
        httpUtils.send(httpMethod, url, requestParams, requestCallBack);
    }
}
