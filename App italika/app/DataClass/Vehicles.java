package com.example.italika.DataClass;

import com.example.italika.Interfaces.Callback;
import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.Request.ConfigSave;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vehicles extends ConfigSave implements Serializable {
    private int id=0;
    private String model="";
    private String year="";
    private String noS="";
    private String plates="";
    private String km="";
    private String kmService="";
    private String brand="";


    public static List<Vehicles> mappingList(JSONObject response){
        List<Vehicles> vehicles=new ArrayList<>();
        try{
            JSONArray array= response.optJSONArray("data");

            for (int i = 0; i <array.length() ; i++) {
                JSONObject ins=array.optJSONObject(i);
                Vehicles v=new Vehicles();
                v.setId(ins.optInt("id"));
                v.setModel(ins.optString("model"));
                v.setYear(ins.optString("year"));
                v.setNoS(ins.optString("noSerie"));
                v.setPlates(ins.optString("plates"));
                v.setBrand(ins.optString("brand"));
                v.setKm(ins.optString("km"));
                v.setKmService(ins.optString("kmService"));
                vehicles.add(v);
            }
        }catch (Exception e){

        }

        return vehicles;
    }

    public static void loadVehicles(final Callback<List<Vehicles>> callback) {
        String url = "vehicle/?get&idLogin="+ Login.Instance().getId();
        Request.executeTimeout(url, new CallbackRequest() {
            @Override
            public void onResult(JSONObject response) {
                if (callback != null) callback.onResult(Vehicles.mappingList(response));
            }

            @Override
            public void onError() {
                if (callback != null) callback.onFail();
            }
        });
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNoS() {
        return noS;
    }

    public void setNoS(String noS) {
        this.noS = noS;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getKmService() {
        return kmService;
    }

    public void setKmService(String kmService) {
        this.kmService = kmService;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
