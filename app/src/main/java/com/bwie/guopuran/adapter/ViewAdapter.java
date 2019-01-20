package com.bwie.guopuran.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends PagerAdapter {
    private List<String> list=new ArrayList<>();
    private Context context;

    public ViewAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @SuppressLint("ClickableViewAccessibility")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView image=new SimpleDraweeView(context);
        image.setImageURI(list.get((position%list.size())));
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        container.addView(image);
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if (monTouch!=null){
                            monTouch.next(true);
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (monTouch!=null){
                            monTouch.next(false);
                        }
                        break;
                }
                return true;
            }
        });
        return image;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    private  onTouch monTouch;
    public void setOnTouch(onTouch monTouch){
        this.monTouch=monTouch;
    }
    public interface onTouch{
        void next(boolean flag);
    }
}
