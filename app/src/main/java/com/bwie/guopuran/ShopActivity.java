package com.bwie.guopuran;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.guopuran.adapter.ViewAdapter;
import com.bwie.guopuran.base.BaseActivity;
import com.bwie.guopuran.bean.ShopBean;
import com.bwie.guopuran.presenter.IpresenterImpl;
import com.bwie.guopuran.view.IView;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @描述 商品详情
 *
 * @创建日期 2019/1/20 16:00
 *
 */
public class ShopActivity extends BaseActivity implements IView {

    private ViewPager viewPager;
    private TextView shop_title;
    private TextView shop_price;
    private TextView shop_go;
    private IpresenterImpl mIpresenterImpl;
    private String url="getProductDetail?pid=%s";
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            sendEmptyMessageDelayed(0,2000);
        }
    };

    @Override
    protected int initId(Bundle savedInstanceState) {
        return R.layout.activity_shop;
    }

    @Override
    protected void initView() {
        //互绑
        initPresenter();
        //获取资源ID
        viewPager = findViewById(R.id.shop_viewpager);
        shop_title = findViewById(R.id.shop_text_title);
        shop_price = findViewById(R.id.shop_text_price);
        shop_go = findViewById(R.id.shop_text_go);
    }

    @Override
    protected void initData() {
        //获取pid
        initIntent();
    }


    private void initIntent() {
        Intent intent=getIntent();
        String pid = intent.getStringExtra("pid");
        //发送请求
        initUrl(pid);
    }

    private void initUrl(String pid) {
        mIpresenterImpl.getRequestter(String.format(url,pid),ShopBean.class);
    }

    //互绑
    private void initPresenter() {
        mIpresenterImpl=new IpresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        mIpresenterImpl.deatch();
        handler.removeMessages(0);
    }

    @Override
    public void getData(Object object) {
        if (object instanceof ShopBean){
            ShopBean shopBean= (ShopBean) object;
            //赋值
            shop_title.setText(shopBean.getData().getTitle());
            shop_price.setText("￥"+shopBean.getData().getPrice());
            String images = shopBean.getData().getImages();
            String[] split = images.split("\\|");
            List<String> list= new ArrayList<>();
            for (int i=0;i<split.length;i++){
                list.add(split[i]);
            }
            ViewAdapter viewAdapter = new ViewAdapter(list,this);
            viewPager.setAdapter(viewAdapter);
            handler.removeMessages(0);
            //发送，进行轮播
            handler.sendEmptyMessageDelayed(0,2000);
            //触摸停止轮播，松开继续
            viewAdapter.setOnTouch(new ViewAdapter.onTouch() {
                @Override
                public void next(boolean flag) {
                    if (flag){
                        handler.removeMessages(0);
                    }else{
                        handler.sendEmptyMessageDelayed(0,2000);
                    }
                }
            });
        }

    }
}
