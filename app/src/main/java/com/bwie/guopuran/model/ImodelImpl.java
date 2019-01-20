package com.bwie.guopuran.model;

import com.bwie.guopuran.util.RetrofitUtil;
import com.google.gson.Gson;

import java.util.Map;

public class ImodelImpl implements Imodel {
    @Override
    public void getReqeustModel(String url, final Class clazz, final MyCallBack callBack) {
        RetrofitUtil.getInstance().get(url, new RetrofitUtil.ICallBack() {
            @Override
            public void getResult(String result) {
                //解析
                Object o = new Gson().fromJson(result, clazz);
                callBack.getData(o);
            }
        });
    }
}
