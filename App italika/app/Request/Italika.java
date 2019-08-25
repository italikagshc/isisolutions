package com.example.italika.Request;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.italika.R;


public final class Italika {

    private volatile static Italika instance = null;

    private static Activity activity;
    public static Context context;
    public static String urlDB = "https://yoenvio.synology.me/italika-sdk/";


    public static class UI {

        public static void message(View view, String msg, int color, int colorText, int length) {
            Snackbar snackbar = Snackbar.make(view, msg, length);
            snackbar.getView().setBackgroundColor(color);

            TextView textView = snackbar.getView().findViewById(R.id.snackbar_text);
            textView.setTextColor(colorText);
            snackbar.show();
        }

    }

    public static class Loader extends Dialog {

        public Loader(@NonNull Context context) {
            super(context);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setContentView(R.layout.dialog_load);
            setCancelable(false);
        }

    }

    public static void setActivity(Activity activity) {
        Italika.activity = activity;
    }

    public static Activity getActivity() {
        return activity;
    }
}
