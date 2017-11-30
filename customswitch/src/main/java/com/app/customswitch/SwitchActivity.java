package com.app.customswitch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

public class SwitchActivity extends AppCompatActivity {

    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        switchButton = (SwitchButton) findViewById(R.id.switchbutton);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String s;
                if (isChecked) {
                    s = "选中";
                } else {
                    s = "未选";
                }
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
