package com.example.italika.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.italika.DataClass.Vehicles;
import com.example.italika.R;

import java.util.List;


public class ListViewVehicleAdapter extends RecyclerView.Adapter<ListViewVehicleAdapter.ViewHolder> {

    private List<Vehicles> list;
    private Context vContext;
    private int idWorking;

    public ListViewVehicleAdapter(Context vContext, List<Vehicles> list) {
        this.vContext= vContext;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_vehicle,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewVehicleAdapter.ViewHolder holder, final int position) {
        if (holder == null) {
            return;
        }
        holder.bindView(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView twPlates,twModel;
        private View layout;
        private int position;


        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            twModel=layout.findViewById(R.id.adapterVehicleModel);
            twPlates=layout.findViewById(R.id.adapterVehiclePlates);


        }

        public void bindView(final int pos) {
            this.position = pos;
            twModel.setText(list.get(position).getBrand());
            twPlates.setText(list.get(position).getPlates());


        }

    }
}
