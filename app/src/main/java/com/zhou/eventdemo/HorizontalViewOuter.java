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
public class HorizontalViewOuter extends View {

    private int xDown;
    private int xMove;
    private int onceMove;


    public HorizontalViewOuter(Context context) {
        super(context);
    }

    public HorizontalViewOuter(Context context, @Nullable AttributeSet attrs) {
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
}
