package com.zhou.eventdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: zhouyunfei
 * @date: 2018/8/1
 * @desc:
 */
public class HorizontalViewInner extends View {

    private int xDown;
    private int xMove;
    private int onceMove;
    private int mLastX;
    private int mLastY;


    public HorizontalViewInner(Context context) {
        super(context);
    }

    public HorizontalViewInner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                onceMove = xMove - xDown;
                layout(getLeft() + onceMove, getTop(), getRight() + onceMove, getBottom());
                break;
        }
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);//不拦截
            }
            break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mLastX) < Math.abs(y - mLastY)-5) {
                    getParent().requestDisallowInterceptTouchEvent(false);//拦截
                }
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }
}
