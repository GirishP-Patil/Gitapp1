package com.example.jobw;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public  class DoWork {
    private static final String TAG="Mainact";

    public static int i=10;

    protected static ArrayList<String> customlist;


    public static  int no()
    {
      int number= new Random().nextInt(100);
      return number;
    }

    public static void savedata(Context c)
    {
        customlist=new ArrayList<String>();
        customlist.add("A");
        customlist.add("B");


        // Create Gson object.
        GsonBuilder gsonBuilder  = new GsonBuilder();
        // Allowing the serialization of static fields
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        // Creates a Gson instance based on the current configuration
        Gson gson = gsonBuilder.create();
        // Gson gson = new Gson();

      //  Emp.id=1;
        Emp.name="Girish Patil";
        Emp ee1=new Emp();
        String user = gson.toJson(ee1);
        SharedPreferences sharedPreferences = c.getSharedPreferences("dbfile1", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("empdetail",user);
        Log.d(TAG, "---------- Data Saving-------");
        Log.d(TAG, "Emp e :"+ee1.getId());
        Log.d(TAG, "emp e :"+ee1.getName());
        editor.putString("key", "data1");
        editor.commit();
        Toast.makeText(c, "User setting saved.", Toast.LENGTH_SHORT).show();

    }
    public   static void loaddata(Context c)
    {
        Log.d(TAG, "--------- load method ------------");


        SharedPreferences settings =c.getSharedPreferences("dbfile1",MODE_PRIVATE);
        String empstring = settings.getString("empdetail", "");

        // Create Gson object.
        GsonBuilder gsonBuilder  = new GsonBuilder();
        // Allowing the serialization of static fields
        gsonBuilder.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        // Creates a Gson instance based on the current configuration
        Gson gson = gsonBuilder.create();
        //Gson gson = new Gson();

        Emp e1 = gson.fromJson(empstring,Emp.class);
        Log.d(TAG, "----------Loaded data  Oject-static --------------");

        Log.d(TAG, "Emp e1 :"+e1.getId());
        Log.d(TAG, "emp e1 :"+e1.getName());
        Log.d(TAG, "Emp static :"+Emp.id);
        Log.d(TAG, "Emp static :"+Emp.name);
        Log.d(TAG,"E s"+empstring);

//        Emp.id=e1.getId();
//        Emp.name=e1.getName();
//        Log.d(TAG, "---------- Oject to static --------------");
//
//        Log.d(TAG, "Emp e1"+e1.getId());
//        Log.d(TAG, "emp e1"+e1.getName());
//        Log.d(TAG, "Emp static"+Emp.id);
//        Log.d(TAG, "Emp static"+Emp.name);

        String keystring = settings.getString("key", "");
        Log.d(TAG, keystring);

    }
}
