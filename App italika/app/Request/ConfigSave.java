package com.example.italika.Request;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ConfigSave {

    protected static void saveObject(String data, Object obj) {
        if (Italika.getActivity() != null) {
            try {
                FileOutputStream fos = Italika.getActivity().openFileOutput(data, Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(obj);
                os.close();
                fos.close();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }

    protected static Object readObject(String data) {
        if (Italika.getActivity() != null) {
            try {
                FileInputStream fis = Italika.getActivity().openFileInput(data);
                ObjectInputStream is = new ObjectInputStream(fis);
                Object obj = is.readObject();
                is.close();
                fis.close();
                return obj;
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }else if(Italika.context!=null){
            try {
                FileInputStream fis = Italika.context.openFileInput(data);
                ObjectInputStream is = new ObjectInputStream(fis);
                Object obj = is.readObject();
                is.close();
                fis.close();
                return obj;
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        return null;
    }


}
