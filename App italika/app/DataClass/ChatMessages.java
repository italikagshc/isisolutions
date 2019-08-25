package com.example.italika.DataClass;

import com.example.italika.Interfaces.Callback;
import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.Request.ConfigSave;
import com.example.italika.Request.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ChatMessages extends ConfigSave implements Serializable {

    private int id=0;
    private String message="";
    private String date="";
    private int readA=0;
    private int readC=0;
    private int readS=0;
    private int rol=0;
    private String nameSender="";


    public static List<ChatMessages> mappingList(JSONObject response){
        List<ChatMessages> messages=new ArrayList<>();
        try{
            JSONArray array= response.optJSONArray("message");
            for (int i = 0; i <array.length() ; i++) {
                JSONObject message=array.optJSONObject(i);
                ChatMessages chat=new ChatMessages();
                chat.setId(message.optInt("id"));
                chat.setReadA(message.optInt("read_a"));
                chat.setReadC(message.optInt("read_c"));
                chat.setReadS(message.optInt("read_s"));
                chat.setDate(message.optString("date_str"));
                chat.setMessage(message.optString("message"));
                chat.setNameSender(message.optJSONObject("sender").optString("name"));
                chat.setRol(message.optJSONObject("sender").optInt("rol"));
                messages.add(chat);
            }

        }catch (Exception e){

        }

        return messages;
    }

    public static void loadMessages(int idIncidence, final Callback<List<ChatMessages>> callback) {
        String url = "chat/?get&no&incidence="+idIncidence;
        Request.executeTimeout(url, new CallbackRequest() {
            @Override
            public void onResult(JSONObject response) {
                    if (callback != null) callback.onResult(ChatMessages.mappingList(response));
            }

            @Override
            public void onError() {
                if (callback != null) callback.onFail();
            }
        });
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReadA() {
        return readA;
    }

    public void setReadA(int readA) {
        this.readA = readA;
    }

    public int getReadC() {
        return readC;
    }

    public void setReadC(int readC) {
        this.readC = readC;
    }

    public int getReadS() {
        return readS;
    }

    public void setReadS(int readS) {
        this.readS = readS;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }
}
