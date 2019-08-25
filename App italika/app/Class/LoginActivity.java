package com.example.italika.Class;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.italika.Interfaces.CallbackRequest;
import com.example.italika.MainActivity;
import com.example.italika.R;
import com.example.italika.Request.Italika;
import com.example.italika.Request.Login;
import com.example.italika.Request.Request;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class LoginActivity extends AppCompatActivity {

    private EditText phone,password;
    private TextView phoneTittle,passwordTittle;
    private String passwordEncrypted;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Request.context(this);

        phone=findViewById(R.id.activityLoginEdtTxtPhone);
        password=findViewById(R.id.activityLoginPassword);
        phoneTittle=findViewById(R.id.activityLoginTwPhone);
        passwordTittle=findViewById(R.id.activityLoginTwPassword);
        linearLayout=findViewById(R.id.activityLoginLinearLayout);

        inputs();

    }

    private void inputs() {
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               phoneTittle.setVisibility(View.VISIBLE);
            }
        });
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              passwordTittle.setVisibility(View.VISIBLE);
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    passwordTittle.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onClickLogin(final View v) {
        v.setEnabled(false);
        byte[] data;
        try {
            data = password.getText().toString().getBytes("UTF-8");
            passwordEncrypted = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final Italika.Loader loader=new Italika.Loader(LoginActivity.this);
        loader.show();
        Request.executeTimeout("oauth/?oauth&phone="+phone.getText().toString()+"&password="+passwordEncrypted, new CallbackRequest() {
            @Override
            public void onResult(JSONObject response) throws Exception {
                if(response.has("login")){
                    loader.dismiss();
                    Login.Instance().setId(response.optJSONObject("login").optInt("id"));
                    Login.Instance().setPhone(response.optJSONObject("login").optString("phone"));
                    Login.Instance().setName(response.optJSONObject("login").optJSONObject("user").optString("name"));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else if(response.has("fail_pwd")){
                    loader.dismiss();
                    v.setEnabled(true);
                    Italika.UI.message(linearLayout,"Contraseña incorrecta, verifica e intenta nuevamente", Color.BLACK,Color.WHITE, Snackbar.LENGTH_SHORT);
                }else if(response.has("fail")){
                    loader.dismiss();
                    v.setEnabled(true);
                    Italika.UI.message(linearLayout,"Usuario no encontrado, verifica e intenta nuevamente", Color.BLACK,Color.WHITE, Snackbar.LENGTH_SHORT);
                }
            }

            @Override
            public void onError() {
                v.setEnabled(true);
                Italika.UI.message(linearLayout,"Error de red, verifica tu conexión", Color.RED,Color.WHITE, Snackbar.LENGTH_SHORT);
            }
        });
    }
}
