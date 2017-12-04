package com.app.workone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by QinQinBaoBei on 2017/12/2.
 */

public class CustomView extends LinearLayout {
    int postion;
    List<ImageView> imgList = new ArrayList<>();
    Handler handler = new Handler();
    int num=0;

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.home_banner,null);
        addView(view);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) view.findViewById(R.id.linear);
    }

    public void setData(final List<String> data, final List<String> listurl) {
        for (int i=0;i<data.size();i++){
            ImageView imageView = new ImageView(getContext());
            imgList.add(imageView);
            Glide.with(getContext()).load(data.get(i)).into(imageView);

             //图片的点击事件
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),WebViewActivity.class);
                    intent.putExtra("url",listurl.get(postion));
                    getContext().startActivity(intent);
                }
            });

            //创建小圆点
            ImageView ivCircle = new ImageView(getContext());
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 5;
            ivCircle.setLayoutParams(layoutParams);

          //  ImageView iv = (ImageView) linearLayout.getChildAt(0);
            ivCircle.setBackgroundResource(R.drawable.shape01);
            linearLayout.addView(ivCircle);
        }
        ImageView iv = (ImageView) linearLayout.getChildAt(0);
        iv.setBackgroundResource(R.drawable.shape02);
        //设置适配器
        MyAdapter myAdapter = new MyAdapter();
        viewPager.setAdapter(myAdapter);
        //设置监听
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        //定时器,无线轮播
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             //无线轮播设置当前条目
                              viewPager.setCurrentItem((num++)%imgList.size());
                         }
                     });
              }
        },1000,3000);
    }
    //重置小圆点
    private void reset() {
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) linearLayout.getChildAt(i);
            iv.setBackgroundResource(R.drawable.shape01);
        }
    }
    class MyPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //重置所有的圆点装点
            position = position;
            reset();
            ImageView iv = (ImageView) linearLayout.getChildAt(position);
            iv.setBackgroundResource(R.drawable.shape02);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    //创建一个适配器
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imgList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
