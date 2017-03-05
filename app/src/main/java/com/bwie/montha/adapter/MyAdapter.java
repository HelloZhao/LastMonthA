package com.bwie.montha.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.montha.R;
import com.bwie.montha.bean.Bean;

import java.util.List;

/**
 * Created by lenovo on 2017/2/23.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private final List<Bean.DataBean> dataList;
    private final Context context;

    public MyAdapter(List<Bean.DataBean> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String title = dataList.get(position).getTitle();
        String[] split = title.split("：");
        holder.name_tv.setText(split[0]);
        Glide.with(context).load(dataList.get(position).getImg()).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);
        holder.age_tv.setText(dataList.get(position).getUserAge()+"");
        holder.work_tv.setText(dataList.get(position).getOccupation());
        holder.des_tv.setText(dataList.get(position).getIntroduction());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView name_tv;
        private final TextView age_tv;
        private final TextView work_tv;
        private final TextView des_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            name_tv = (TextView) itemView.findViewById(R.id.name_tv);
            age_tv = (TextView) itemView.findViewById(R.id.age_tv);
            work_tv = (TextView) itemView.findViewById(R.id.work_tv);
            des_tv = (TextView) itemView.findViewById(R.id.des_tv);
        }
    }
}
