package com.example.italika;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.Request.Italika;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private EditText model,year,plates,noS,km;
    private TextView modelTw,yearTw,platesTw,noSTw,kmTw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Request.context(this);

        model=findViewById(R.id.activityAddRegisterEdtTxtModel);
        coordinatorLayout=findViewById(R.id.activityRegisterCoordinator);
        year=findViewById(R.id.activityAddRegisterEdtTxtYear);
        plates=findViewById(R.id.activityAddRegisterEdtTxtPlates);
        noS=findViewById(R.id.activityAddRegisterEdtTxtNoSerie);
        km=findViewById(R.id.activityAddRegisterEdtTxtKm);
        modelTw=findViewById(R.id.activityAddRegisterTvModel);
        yearTw=findViewById(R.id.activityAddRegisterTvYear);
        platesTw=findViewById(R.id.activityAddRegisterTvPlates);
        noSTw=findViewById(R.id.activityAddRegisterTvNoSerie);
        kmTw=findViewById(R.id.activityAddRegisterTvKm);

        inputs();

    }

    private void inputs() {
        model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelTw.setVisibility(View.VISIBLE);
            }
        });

        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year.setVisibility(View.VISIBLE);
            }
        });

        year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    yearTw.setVisibility(View.VISIBLE);
                }
            }
        });

        plates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                platesTw.setVisibility(View.VISIBLE);
            }
        });

        plates.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    platesTw.setVisibility(View.VISIBLE);
                }
            }
        });

        noS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noSTw.setVisibility(View.VISIBLE);
            }
        });

        noS.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    noSTw.setVisibility(View.VISIBLE);
                }
            }
        });

        km.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kmTw.setVisibility(View.VISIBLE);
            }
        });

        km.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    kmTw.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onClickSave(final View view) {
        view.setEnabled(false);
        final Italika.Loader loader=new Italika.Loader(this);
        loader.show();
        if(!model.getText().toString().isEmpty() && !year.getText().toString().isEmpty() && !noS.getText().toString().isEmpty()
           && !plates.getText().toString().isEmpty()){
            Request.executeTimeout("vehicle/?post&idLogin="+ Login.Instance().getId()+"&model="+model.getText().toString()+"&year="+year.getText().toString()
                    +"&noSerie="+noS.getText().toString()+"&plates="+plates.getText().toString()+"&brand=Italika", new CallbackRequest() {
                @Override
                public void onResult(JSONObject response) throws Exception {
                    loader.dismiss();
                    Toast.makeText(RegisterActivity.this, "Se guardo esta motocicleta a tu perfil de manera exitosa", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError() {
                    loader.dismiss();
                    view.setEnabled(true);
                    Italika.UI.message(coordinatorLayout,"Verifica tu conexion y vuelve a intentarlo", Color.RED,Color.WHITE, Snackbar.LENGTH_SHORT);
                }
            });
        }else {
            loader.dismiss();
            view.setEnabled(true);
            Italika.UI.message(coordinatorLayout,"Es importante complentar todos los campos para guardar el información", Color.BLACK,Color.WHITE, Snackbar.LENGTH_SHORT);
        }

    }

    public void onClickCancel(View view) {
        onBackPressed();
    }
}
