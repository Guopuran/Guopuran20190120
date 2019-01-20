package com.bwie.guopuran.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwie.guopuran.view.IView;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initId(savedInstanceState));
        initView();
        initData();
    }


    protected abstract int initId(Bundle savedInstanceState);

    protected abstract void initView();

    protected abstract void initData();

}
