package com.bwie.guopuran.bean;

public class MsgBean {
    private Object object;
    private String flag;

    public MsgBean(Object object, String flag) {
        this.object = object;
        this.flag = flag;
    }

    public MsgBean() {

    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
