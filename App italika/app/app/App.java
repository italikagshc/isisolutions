package com.example.italika.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.Request.Italika;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONObject;

public class App  extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        Italika.context = this;
        Request.context(this);

        if(Login.Instance().getId() != 0){
            //methodMessaging();
        }

    }

    public static void methodMessaging() {
        Task<InstanceIdResult> task = FirebaseInstanceId.getInstance().getInstanceId();
        task.addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult authResult) {
                String fcmToken = authResult.getToken();
                Request.execute("ft/?token="+fcmToken+"&idLogin="+ Login.Instance().getId(), null);
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public static Context getContext(){
        return app.getApplicationContext();
    }

}
