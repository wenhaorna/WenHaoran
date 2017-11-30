package com.app.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/30.
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        /**
         * 一般是定义为int top;一个top实际上是数组的下标
         left ： 指定矩形框左上角的x坐标
         top： 指定矩形框左上角的y坐标
         right： 指定矩形框右下角的x坐标
         bottom：指定矩形框右下角的y坐标
         */
        int width = getWidth();
        int tw = 0;
        int th = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (tw + child.getWidth() < width) {

            } else {
                tw = 0;
                th += child.getMeasuredHeight();   //超过屏幕的宽度，自动换行
            }

            child.layout(tw, th, tw + child.getMeasuredWidth(), th + child.getMeasuredHeight());
            tw += child.getMeasuredWidth();
        }
    }
}