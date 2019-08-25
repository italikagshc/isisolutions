package com.example.italika.Interfaces;


public interface Callback<Type> {

    void onResult(Type result);

    void onFail();

}
