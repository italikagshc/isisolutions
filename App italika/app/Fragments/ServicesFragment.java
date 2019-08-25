package com.example.italika.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.italika.Adapters.ListViewListServices;
import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.R;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class ServicesFragment extends Fragment {

    ArrayList<ListViewListServices.ServiceList> list = new ArrayList<>();
    private RecyclerView recilerList;
    private ListViewListServices adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v=inflater.inflate(R.layout.fragment_services, container, false);
        recilerList = v.findViewById(R.id.serviceRecicler);



        list = new ArrayList<>();
        Request.executeTimeout("service/?get&idLogin=" + Login.Instance().getId(), new CallbackRequest() {
            @Override
            public void onResult(JSONObject response) throws Exception {
                JSONArray array = response.optJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    ListViewListServices.ServiceList temp = new ListViewListServices.ServiceList();
                    temp.id = array.optJSONObject(i).optInt("id");
                    temp.idVehiclle = array.optJSONObject(i).optString("idVehicle");
                    temp.note = array.optJSONObject(i).optString("note");
                    temp.km =  array.optJSONObject(i).optString("km");
                    temp.date =  array.optJSONObject(i).optString("date");
                    temp.time =  array.optJSONObject(i).optString("time");
                    temp.dateInit = array.optJSONObject(i).optString("dateInit");
                    temp.dateOut = array.optJSONObject(i).optString("dateOut");
                    temp.total = array.optJSONObject(i).optJSONObject("total").optString("total");
                    JSONArray ref = array.optJSONObject(i).optJSONArray("refactions");
                    for (int j = 0; j < ref.length(); j++) {
                        ListViewListServices.refaction r = new ListViewListServices.refaction();
                        r.name = ref.getJSONObject(i).optString("name");
                        r.note = ref.getJSONObject(i).optString("note");
                        r.price = ref.getJSONObject(i).optString("price");
                        temp.listRefaction.add(r);
                    }
                    list.add(temp);
                   // adapter.notifyDataSetChanged();
                }

                recilerList.setHasFixedSize(true);
                recilerList.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new ListViewListServices(getContext(), list);
                recilerList.setAdapter(adapter);
                Log.e("size",  list.size() + "  ");
            }

            @Override
            public void onError() {
                Log.e("Error",  "E - - - - - -  ");
            }
        });

        return v;
    }

}
