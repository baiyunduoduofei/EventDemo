package com.zhou.eventdemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: zhouyunfei
 * @date: 2018/8/1
 * @desc:
 */
public class VerticalViewInner extends View {
    private int yDown;
    private int yMove;
    private int onceMove;
    private int mLastX;
    private int mLastY;


    public VerticalViewInner(Context context) {
        super(context);
    }

    public VerticalViewInner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                Log.e("zhou","yDown=="+y);
                yDown = y;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                onceMove = yMove - yDown;
                Log.e("zhou","onceMove=="+onceMove);
                layout(getLeft(), getTop() + onceMove, getRight(), getBottom() + onceMove);
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
                if (Math.abs(x - mLastX) -5> Math.abs(y - mLastY)) {
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
