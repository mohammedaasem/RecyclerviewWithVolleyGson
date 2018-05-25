package com.aasem.recyclerviewwithvolleyexample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aasem.recyclerviewwithvolleyexample.Data.FeatureData;
import com.aasem.recyclerviewwithvolleyexample.DetailActivity;
import com.aasem.recyclerviewwithvolleyexample.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inspire_info_soft on 5/12/2018.
 */

public class FeatureAdapter extends RecyclerView.Adapter<FeatureAdapter.MyViewHolder> {

    Context context;
    List<FeatureData> featureData;

    public FeatureAdapter(Context context,List<FeatureData> featureData) {
        this.context = context;
        this.featureData = featureData;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.custom_design_feature, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide
                .with(context)
                .load(featureData.get(position).getThumbnailUrl())
                .centerCrop()
                .into(holder.ivImage);
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDetailActivity(featureData.get(position).getThumbnailUrl(),featureData.get(position).getTitle(),featureData.get(position).getUrl());
            }
        });
    }

    private void openDetailActivity(String thumbnailUrl, String title, String url) {
        Intent intent=new Intent(context,DetailActivity.class);
        intent.putExtra("thumbnailUrl",thumbnailUrl);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return featureData.size();
    }

}
