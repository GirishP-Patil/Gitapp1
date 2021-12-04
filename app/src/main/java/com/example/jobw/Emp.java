package com.example.jobw;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

public class Emp {

  //  private static final String TAG="Mainlog";

    static {


    }
        static int id=100;
        static String name="patilpatill";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Emp()
    {}
}
