package com.aasem.recyclerviewwithvolleyexample.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aasem.recyclerviewwithvolleyexample.Data.ListData;
import com.aasem.recyclerviewwithvolleyexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inspire_info_soft on 5/12/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    List<ListData> listData;

    public ListAdapter(Context context, List<ListData> listData) {
        this.context = context;
        this.listData = listData;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.custom_design_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvTitle.setText(listData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
