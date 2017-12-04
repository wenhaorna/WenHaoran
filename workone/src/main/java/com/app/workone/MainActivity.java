package com.app.workone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private CustomView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_home);
        banner = (CustomView) findViewById(R.id.mybanner);
        getData();
    }

    public void getData() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://120.27.23.105/ad/getAd")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final List<String> list = new ArrayList<>();
                final List<String> listurl = new ArrayList<>();

                String string = response.body().string();
                final DataDataBean dataDataBean = new Gson().fromJson(string, DataDataBean.class);
                //将接口图片拿到,将图片的地址和url一起传过去.
                for (int i = 0; i < dataDataBean.getData().size(); i++) {
                    list.add(dataDataBean.getData().get(i).getIcon());
                    listurl.add(dataDataBean.getData().get(i).getUrl());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        banner.setData(list, listurl);
                    }
                });
            }
        });
    }
}
