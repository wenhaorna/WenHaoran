package com.app.titleactivitydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        TitleBar titleBar = (TitleBar) findViewById(R.id.titlebar);
        titleBar.setListener(new TitleBar.OnListener() {
            @Override
            public void onLeftListener() {
                Toast.makeText(getApplicationContext(), "点击返回，可在此处调用finish()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightListener() {
                Toast.makeText(getApplicationContext(), "我是提交按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
