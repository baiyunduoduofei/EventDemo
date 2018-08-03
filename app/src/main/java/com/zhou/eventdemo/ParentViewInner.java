package com.zhou.eventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author: zhouyunfei
 * @date: 2018/8/1
 * @desc:
 */
public class ParentViewInner extends FrameLayout {

    private int yDown;
    private int yMove;
    private int xDown;
    private int xMove;
    private int onceMoveX;
    private int onceMoveY;



    public ParentViewInner(Context context) {
        super(context);
    }

    public ParentViewInner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                onceMoveX = xMove - xDown;
                onceMoveY = yMove - yDown;
                layout(getLeft() + onceMoveX, getTop() + onceMoveY, getRight() + onceMoveX, getBottom() + onceMoveY);
                break;

        }
        return true;
    }


    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xDown = (int) event.getX();
            yDown = (int) event.getY();
            return false;
        }
        return true;
    }

}
