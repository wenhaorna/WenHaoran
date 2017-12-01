package com.app.titleactivitydemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/1.
 */

public class TitleBar extends LinearLayout {

    private OnListener listener;
    private LinearLayout left;
    private TextView right;

    public interface OnListener {
        void onLeftListener();

        void onRightListener();
    }

    public void setListener(OnListener listener) {
        this.listener = listener;
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.titlebar, this);
        left = (LinearLayout) findViewById(R.id.left_layout);
        right = (TextView) findViewById(R.id.right_text);

        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLeftListener();
                }
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRightListener();
                }
            }
        });
    }
}
