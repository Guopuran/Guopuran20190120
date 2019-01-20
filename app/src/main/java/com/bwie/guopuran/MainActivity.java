package com.bwie.guopuran;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.guopuran.adapter.SearchAdapter;
import com.bwie.guopuran.base.BaseActivity;
import com.bwie.guopuran.bean.SearchBean;
import com.bwie.guopuran.dao.UserDao;
import com.bwie.guopuran.dao.UserDaoDao;
import com.bwie.guopuran.presenter.IpresenterImpl;
import com.bwie.guopuran.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @描述  展示界面
 *
 * @创建日期 2019/1/20 14:46
 *
 */
public class MainActivity extends BaseActivity implements IView {

    private IpresenterImpl mIpresenterImpl;
    private RecyclerView main_recy;
    private Button main_gps;
    private String url="searchProducts?keywords=笔记本&page=1";
    private SearchAdapter searchAdapter;
    private UserDaoDao userDaoDao;

    @Override
    protected int initId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //互绑
        initPresenter();
        //获取资源ID
        main_gps = findViewById(R.id.main_button_gps);
        main_recy = findViewById(R.id.main_recy);
        userDaoDao = App.getmDaoSession().getUserDaoDao();
    }

    @Override
    protected void initData() {

        //initIsCheck();
        //初始化RecyclerView
        initRecy();
        main_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GpsActivity.class);
                startActivity(intent);
            }
        });
    }

   private boolean initIsCheck() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
       return activeNetworkInfo!=null&&activeNetworkInfo.isAvailable();
   }


    private void initRecy() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //判断网络状态
        main_recy.setLayoutManager(linearLayoutManager);
        searchAdapter = new SearchAdapter(this);
        main_recy.setAdapter(searchAdapter);
        if (!initIsCheck()){
            List<SearchBean.DataBean> list=new ArrayList<>();
            List<UserDao> userDaos = userDaoDao.loadAll();
            for (int i=0;i<userDaos.size();i++){
                list.add(new SearchBean.DataBean(userDaos.get(i).getImage(),userDaos.get(i).getPrice(),userDaos.get(i).getNum(),userDaos.get(i).getTitle()));
            }
            searchAdapter.setList(list);
            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
            return ;
        }
        //发送请求
        initUrl();
        initDelete();
    }

    private void initDelete() {
        searchAdapter.setDeleteItem(new SearchAdapter.deleteItem() {
            @Override
            public void delete(View v, int position) {
                searchAdapter.initDelete(v,position);
            }
        });
    }

    private void initUrl() {
        mIpresenterImpl.getRequestter(url,SearchBean.class);
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

    }

    @Override
    public void getData(Object object) {
        if (object instanceof SearchBean){
            SearchBean searchBean= (SearchBean) object;
            List<SearchBean.DataBean> data = searchBean.getData();

            for (int i=0;i<data.size();i++){
                UserDao userDao=new UserDao(null,data.get(i).getImages(),data.get(i).getTitle(),data.get(i).getPrice(),data.get(i).getSalenum());
                userDaoDao.insertOrReplace(userDao);
            }
            Log.i("TAG",userDaoDao.loadAll().get(1).getTitle()+"");
            searchAdapter.setList(searchBean.getData());

        }
    }
}
