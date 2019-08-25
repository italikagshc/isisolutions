package com.example.italika.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.italika.Adapters.ListViewVehicleAdapter;
import com.example.italika.Class.LoginActivity;
import com.example.italika.DataClass.Vehicles;
import com.example.italika.Interfaces.Callback;
import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.R;
import com.example.italika.RegisterActivity;
import com.example.italika.Request.Request;
import com.example.italika.ShoopingOnlineActivity;

import org.json.JSONObject;

import java.util.List;


public class StartFragment extends Fragment {

    private LinearLayout linearLayoutNotResult;
    private FloatingActionButton add,shoppOnline;
    private Button addLlR,shoppOnlineLlR;
    private RecyclerView recyclerView;
    private ListViewVehicleAdapter listViewVehicleAdapter;
    private LinearLayoutManager layoutManagerOrientation;
    private boolean resume=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View v=inflater.inflate(R.layout.fragment_start, container, false);

        linearLayoutNotResult=v.findViewById(R.id.fragmentStartLlNotResut);
        add=v.findViewById(R.id.fragmentStartButtonAdd);
        shoppOnline=v.findViewById(R.id.fragmentStartButtonShopping);
        addLlR=v.findViewById(R.id.fragmentStartButtonAddLl);
        shoppOnlineLlR=v.findViewById(R.id.fragmentStartButtonShopingLl);
        recyclerView=v.findViewById(R.id.fragmentStartReciclerView);

        Vehicles.loadVehicles(new Callback<List<Vehicles>>() {
            @Override
            public void onResult(List<Vehicles> result) {
                if (result.size()>0){
                    layoutManagerOrientation = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setVisibility(View.VISIBLE);
                    listViewVehicleAdapter=new ListViewVehicleAdapter(getContext(),result);
                    recyclerView.setAdapter(listViewVehicleAdapter);

                }else {
                    linearLayoutNotResult.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail() {

            }
        });

        buttons();

        return v;
    }

    private void buttons() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplication(), RegisterActivity.class));
            }
        });

        addLlR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplication(), RegisterActivity.class));
            }
        });

        shoppOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity().getApplication(), ShoopingOnlineActivity.class));
            }
        });

        shoppOnlineLlR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplication(), ShoopingOnlineActivity.class));
            }
        });
    }


    @Override
    public void onPause() {
        resume=true;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (resume){
            Vehicles.loadVehicles(new Callback<List<Vehicles>>() {
                @Override
                public void onResult(List<Vehicles> result) {
                    if (result.size()>0){
                        layoutManagerOrientation = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setVisibility(View.VISIBLE);
                        listViewVehicleAdapter=new ListViewVehicleAdapter(getContext(),result);
                        recyclerView.setAdapter(listViewVehicleAdapter);

                    }else {
                        linearLayoutNotResult.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFail() {

                }
            });
        }

    }
}
