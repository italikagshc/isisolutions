package com.example.italika.Request;

import android.util.Log;

import java.io.Serializable;


public final class Login extends ConfigSave implements Serializable {

    private static Login instance=null;
    private static  final String LOGIN_SESSION ="session.italika";



    public static Login Instance(){
        if(instance==null){
            instance = (Login) readObject(LOGIN_SESSION);
            if(instance==null)
                instance= new Login();
        }
        return instance;
    }

    private int id=0;
    private String phone="";
    private String name="";



    private Login(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        instance.id = id;
        Log.e("id",id+"");
        saveObject(LOGIN_SESSION, instance);
    }

    public String getPhone() {return phone;}

    public void setPhone(String phone) {
        instance.phone = phone;
        Log.e("phone",phone+"");
        saveObject(LOGIN_SESSION,instance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        instance.name = name;
        Log.e("name",name+"");
        saveObject(LOGIN_SESSION,instance);
    }
}
