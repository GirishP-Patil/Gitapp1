package com.example.jobw;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.annotation.SuppressLint;
import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.media.Image;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    Button b1, b2;
    private static final String TAG="Mainact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, " Main : start Oncreate  " );



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("ResourceType")
    public void setWallpaper(View v) throws IOException {

    }


    public void stopService(View v) {
        JobScheduler scheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        scheduler.cancel(6756);
        Log.d(TAG,"JOB Schedular cancel");
        try {
            Thread.sleep(5000);
            for (int i = 0; i < 2; i++) {
                Toast.makeText(this, "Job Scheduled Cancle." + i, Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startservice(View v) {

        int mainact_i=5;

        //i Will be 5
        DoWork.i=5;

        Log.i(TAG, " Start : startservice methode on button clicked  " );

        ComponentName componentName = new ComponentName(this, Jobexampl.class);

        JobInfo info = new JobInfo.Builder(6756, componentName).setPersisted(true).setPeriodic(16 * 60 * 1000).build();

        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        int resultcode = scheduler.schedule(info);
        if (resultcode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Main : SaveData Callaed");

            DoWork.savedata(getApplicationContext());

            Log.d(TAG, "Scheduled");

            try {
                Thread.sleep(5000);
                for (int i = 0; i < 2; i++) {
                    Toast.makeText(this, "MAIN : Scheduled." + i, Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        } else {
            Log.d(TAG, "failed");
            try {
                Thread.sleep(5000);
                for(int i=0;i<2;i++) {
                    Toast.makeText(this, "Job Schedule failed."+i , Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //savedata
   //          Log.i(TAG, " start Oncreate  " );
//        Toast.makeText(getApplicationContext(), "User setting saved in sharedpreferences file.", Toast.LENGTH_SHORT).show();


    }
}