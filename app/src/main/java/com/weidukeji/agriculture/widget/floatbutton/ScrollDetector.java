package com.weidukeji.agriculture.widget.floatbutton;

import android.content.Context;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Implementation of {@link android.view.View.OnTouchListener} which uses {@link android.view.GestureDetector} to recognize scrolling events.
 */
public abstract class ScrollDetector extends GestureDetector.SimpleOnGestureListener implements View.OnTouchListener {
    private final GestureDetector mDetector;
    private final int mSlop;

    private float mDownY;
    private boolean mDirection;
    private boolean mIgnore;

    /**
     * Called when {@link #onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)} recognize direction of user gesture as DOWN.
     */
    public abstract void onScrollDown();

    /**
     * Called when {@link #onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)} recognize direction of user gesture as UP.
     */
    public abstract void onScrollUp();

    /**
     * Disable/enable handling of {@link #onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)}.
     *
     * @param ignore true - disable, false - enable
     */
    public void setIgnore(boolean ignore) {
        mIgnore = ignore;
    }

    public ScrollDetector(Context context) {
        mDetector = new GestureDetector(context, this);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.FROYO) {
            //noinspection deprecation
            mSlop = ViewConfiguration.getTouchSlop() * 2;
        } else {
            mSlop = ViewConfiguration.get(context).getScaledPagingTouchSlop();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDetector.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mDownY = e.getY();
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (mIgnore) {
            return false;
        }

        if (mDirection != distanceY > 0) {
            mDirection = !mDirection;
            mDownY = e2.getY();
        }

        float distance = mDownY - e2.getY();

        if (distance < -mSlop) {
            onScrollDown();
        } else if (distance > mSlop) {
            onScrollUp();
        }

        return false;
    }
}
