package com.example.deprem.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deprem.R;
import com.example.deprem.model.depremModel;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {
    private ArrayList<depremModel>depremList;
    private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};


    public RecyclerViewAdapter(ArrayList<depremModel> depremList) {
        this.depremList = depremList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
                holder.bind(depremList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return depremList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textYer;
        TextView textTarih;
        TextView textSaat;
        TextView textDerinlik;
        TextView textSiddet;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind(depremModel genels,String[]colors,Integer position)
        {itemView.setBackgroundColor(Color.parseColor(colors[position %8]));
            textYer=itemView.findViewById(R.id.text_name);
            textTarih=itemView.findViewById(R.id.text_phone);
            textSaat=itemView.findViewById(R.id.text_rating);
            textDerinlik=itemView.findViewById(R.id.text_lati);
            textSiddet=itemView.findViewById(R.id.text_longi);
            textYer.setText(genels.yer);
            textTarih.setText(genels.tarih);
            textSaat.setText(genels.saat);
            textDerinlik.setText(genels.derinlik);
            textSiddet.setText(genels.siddet);




        }
    }
}
