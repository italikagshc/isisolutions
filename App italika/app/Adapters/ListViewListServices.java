package com.example.italika.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.italika.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewListServices extends RecyclerView.Adapter<ListViewListServices.ViewHolder> {

    private List<ServiceList> list;
    private Context vContext;
    private int idPersonal;

    public ListViewListServices(Context vContext, List<ServiceList> list) {
        this.vContext= vContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_services,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewListServices.ViewHolder holder, final int position) {
        if (holder == null) {
            return;
        }
        holder.bindView(position);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idPersonal=list.get(position).id;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView twName,twRol;
        private View layout;
        private int position;


        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            ((TextView)layout.findViewById(R.id.listServicesDate)).setText(list.get(position).date);
            //twName=layout.findViewById(R.id.adapterListPersonalTwName);
            //twRol=layout.findViewById(R.id.adapterListPersonalTwRol);
        }

        public void bindView(final int pos) {
            this.position = pos;
        }

    }

    public static class ServiceList {

        public int id;
        public String idLogin="";
        public String idVehiclle="";
        public String note="";
        public String km="";
        public String date="";
        public String time="";
        public String dateInit="";
        public String dateOut="";
        public int status=0;
        public String total="";
        public ArrayList<refaction> listRefaction = new ArrayList<>();

    }

    public static class refaction {

        public String name;
        public String price;
        public String note;

    }

}
