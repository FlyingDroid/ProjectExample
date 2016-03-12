package com.weidukeji.agriculture.widget;

import android.content.Context;
import android.view.MotionEvent;

/**
 * Created by zhsheng on 2015/1/5.
 */
public class FixedViewpager extends LazyViewPager {
    private boolean TOUCH_MODE=false;
    public FixedViewpager(Context context) {
        super(context);
    }
    public FixedViewpager(Context context,boolean istouch_move){
        this(context);
        this.TOUCH_MODE=istouch_move;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(TOUCH_MODE){
            return super.onInterceptTouchEvent(ev);
        }else{
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(TOUCH_MODE){
            return super.onTouchEvent(ev);
        }
        return false;
    }
}
