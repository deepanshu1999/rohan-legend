package com.example.hp.adjonline;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

List<ListData> data= Collections.emptyList() ;
private Context context;

public Adapter(Context context,List<ListData> data)
{
    this.context=context;
    this.data=data;
}

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        MyHolder myHolder=new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder= (MyHolder) holder;
        ListData current=data.get(position);
        myHolder.textcase.setText(current.getText1());
        myHolder.textbefore.setText("Before: " + current.getJudge());
        myHolder.textdate.setText(current.getDate());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textcase;
        TextView textbefore;
        TextView textdate;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textcase = (TextView) itemView.findViewById(R.id.textViewHead);
            textbefore = (TextView) itemView.findViewById(R.id.textViewDesc);
            textdate = (TextView) itemView.findViewById(R.id.textViewDate);
        }

    }
}
