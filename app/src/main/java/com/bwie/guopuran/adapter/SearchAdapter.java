package com.bwie.guopuran.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.guopuran.R;
import com.bwie.guopuran.ShopActivity;
import com.bwie.guopuran.bean.MsgBean;
import com.bwie.guopuran.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchBean.DataBean> list;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<SearchBean.DataBean> mlist) {
        if (mlist!=null){
            list.addAll(mlist);
        }
        notifyDataSetChanged();
    }
    public SearchBean.DataBean getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recy, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.getdata(getItem(i),context,i);
    }
    public void initDelete(final View v , final int position){
        final float y = v.getX();
        ObjectAnimator animator=ObjectAnimator.ofFloat(v,"translationX",0,500);
        animator.setDuration(1000);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                list.remove(position);
                notifyDataSetChanged();
                v.setX(y);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView item_image;
        private TextView text_title;
        private TextView text_price;
        private TextView text_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取资源ID
            item_image=itemView.findViewById(R.id.recy_item_image);
            text_title=itemView.findViewById(R.id.recy_item_text_title);
            text_price=itemView.findViewById(R.id.recy_item_text_price);
            text_num=itemView.findViewById(R.id.recy_item_text_num);
        }

        public void getdata(final SearchBean.DataBean item, final Context context, final int i) {
            String[] split = item.getImages().split("\\|");
            item_image.setImageURI(Uri.parse(split[0]));
            text_title.setText(item.getTitle());
            text_price.setText("￥"+item.getPrice());
            text_num.setText(item.getSalenum()+"条评论");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,ShopActivity.class);
                    intent.putExtra("pid",String.valueOf(item.getPid()));
                    context.startActivity(intent);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mdeleteItem!=null){
                        mdeleteItem.delete(v,i);
                    }
                    return true;
                }
            });
        }
    }
    private deleteItem mdeleteItem;
    public void setDeleteItem(deleteItem mdeleteItem){
        this.mdeleteItem=mdeleteItem;
    }

    public interface deleteItem{
        void delete(View v ,int position);
    }
}
