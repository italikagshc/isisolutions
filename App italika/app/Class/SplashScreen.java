package com.example.italika.Class;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.italika.MainActivity;
import com.example.italika.R;
import com.example.italika.Request.Italika;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Italika.setActivity(this);
        Request.context(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Login.Instance().getId()!=0){
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    finish();
                }

            }
        },3000);
    }
}
