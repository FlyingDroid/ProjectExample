package com.weidukeji.quality.engine;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhsheng on 2015/1/5.
 */

public abstract class WdBaseAdapter<T> extends BaseAdapter {
    public List<T> lists;
    public Context context;
    public WdBaseAdapter(Context context, List<T> lists) {
        super();
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
