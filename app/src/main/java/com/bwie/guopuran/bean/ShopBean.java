package com.bwie.guopuran.bean;

import java.util.List;
/**
 *
 * @描述 商品详情的Bean类
 *
 * @创建日期 2019/1/20 15:34
 *
 */
public class ShopBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String images;
        private double price;
        private String title;

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
