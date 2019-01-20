package com.bwie.guopuran.presenter;

import com.bwie.guopuran.model.Imodel;
import com.bwie.guopuran.model.ImodelImpl;
import com.bwie.guopuran.model.MyCallBack;
import com.bwie.guopuran.view.IView;

public class IpresenterImpl implements Ipresenter{
    private ImodelImpl mImodelImpl;
    private IView mIView;

    public IpresenterImpl(IView mIView) {
        this.mIView = mIView;
        //实例化
        mImodelImpl=new ImodelImpl();
    }
    //解绑
    public void deatch(){
        if (mImodelImpl!=null){
            mImodelImpl=null;
        }
        if (mIView!=null){
            mIView=null;
        }
    }
    @Override
    public void getRequestter(String url, Class clazz) {
        mImodelImpl.getReqeustModel(url,clazz, new MyCallBack() {
            @Override
            public void getData(Object object) {
                mIView.getData(object);
            }
        });
    }
}
