package com.example.sugarorm_dagger2.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sugarorm_dagger2.R;
import com.example.sugarorm_dagger2.di.component.DaggerDataComponent;
import com.example.sugarorm_dagger2.di.component.DataComponent;
import com.example.sugarorm_dagger2.di.model.Chronometer_model;
import com.example.sugarorm_dagger2.di.model.Data;
import com.example.sugarorm_dagger2.di.module.DataModule;

import java.util.ArrayList;

/**
 * Created by Aliyari on 1/16/2018.
 */

public class ChronometerAdapter extends RecyclerView.Adapter<ChronometerAdapter.ViewHolder> {

    Context context;
    ArrayList<Data> list;

    public ChronometerAdapter(Context context, ArrayList<Data> arrayList) {

        this.context = context;
        this.list = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.chronom_row, parent, false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        DataComponent component = DaggerDataComponent.builder().dataModule(new DataModule()).build();
        Data data = component.provideData();
        Chronometer_model ch = component.providChronometer();


        data = list.get(position);
        holder.year.setText(data.getYears());
        holder.month.setText(data.getMonth());
        holder.day.setText(data.getDay());
        holder.time.setText(data.getTime());
        holder.choron.setText(data.getChronometer());
        holder.id.setText(String.valueOf(data.getId()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Data data=list.get(position);
                Chronometer_model.delete(data);
                removeItem(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView year, month, day, time, choron, id;
        ImageView delete;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtv_id);
            year = itemView.findViewById(R.id.txtv_years);
            month = itemView.findViewById(R.id.txtv_month);
            day = itemView.findViewById(R.id.txtv_day);
            time = itemView.findViewById(R.id.txtv_time);
            choron = itemView.findViewById(R.id.txtv_Chronom);
            delete = itemView.findViewById(R.id.img_del);

        }

    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
}
