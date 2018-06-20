package com.example.anilturan.sozlukapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anilturan.sozlukapp.model.Definition;

import java.util.ArrayList;

public class MeanAdapter extends RecyclerView.Adapter<MeanAdapter.MyViewHolder> {
    ArrayList<Definition> dataList;
    LayoutInflater inflater;

    public MeanAdapter(Context context) {
        dataList = new ArrayList<Definition>();
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.definition1.setText(dataList.get(position).getFirst());
        holder.definition2.setText(dataList.get(position).getSecond());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView definition1;
        private TextView definition2;


        public MyViewHolder(View itemView) {
            super(itemView);
            definition1 = itemView.findViewById(R.id.definition1);
            definition2 = itemView.findViewById(R.id.definition2);
        }
    }

    public void setList(ArrayList<Definition> data) {
        dataList = new ArrayList<>();
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }
}
