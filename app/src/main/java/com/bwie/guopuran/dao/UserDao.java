package com.bwie.guopuran.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserDao {
    @Id(autoincrement = true)
    private Long id;
    private String image;
    private String title;
    private double price;
    private int num;
    @Generated(hash = 931175221)
    public UserDao(Long id, String image, String title, double price, int num) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
        this.num = num;
    }
    @Generated(hash = 917059161)
    public UserDao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}
