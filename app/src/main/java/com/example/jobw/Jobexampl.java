package com.example.jobw;

import android.app.WallpaperManager;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Jobexampl extends JobService {
    private static final String Tag="Mainact";
    private boolean flag=false;
    public static  int i=0;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(Tag,"JOB SE: On startJob Methode : Job Service Started ");
        Log.i(Tag, "JOB SE: Load Data from sharepreferences " );
        DoWork.loaddata(getApplicationContext());
        Log.d(Tag,"JOB : Ater Dowork.loaddata :");
        Log.d(Tag,"Emp "+ Emp.id);


        Log.d(Tag,"JOB SE:  Dobackground Called ");
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doBackgroundwork(params);

        Emp.id= Emp.id+1;
        Log.d(Tag,"JOB SE: EMP ID "+ Emp.id);

        DoWork.savedata(getApplication());



        return false;
    }

    private void doBackgroundwork(JobParameters params) {
        Log.d(Tag,"JOB SE:Inside Do Background Work Method");

        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(Tag," ----------------- List : ---------------------");
           for(String s:DoWork.customlist)
        {
            Log.d(Tag,"List : "+s);

        }

        try {
            Thread.sleep(1000);
            i++;


            WallpaperManager wallpaperManager= WallpaperManager.getInstance(getApplicationContext());
            Log.d(Tag,"Bitmap created ");
            // --------- Empty Bitmap
            Bitmap bitmap = Bitmap.createBitmap(1000, 2000, Bitmap.Config.ARGB_8888);
            System.out.println("---------bitmap----------------------");
            // ----- Paint obj for drawing
            Paint paint = new Paint();

            System.out.println("---------- 1");
            Canvas canvas = new Canvas(bitmap);
            //------------ Background Color ------------
            System.out.println("---------- 1");
            paint.setColor(Color.rgb( new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawPaint(paint);

            //--------- Fond size----
            double relation = Math.sqrt(canvas.getWidth() * canvas.getHeight());
            System.out.println("w"+canvas.getWidth());
            System.out.println("h"+canvas.getHeight());
            relation = relation / 250;


            //------------ main Words foreground Color and style ----
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setTextSize((float) (21*relation));
            paint.setShadowLayer(3f, 5f, 5f, Color.BLACK);
            //paint.setFakeBoldText(true);
            paint.setTextAlign(Paint.Align.CENTER);

            //  canvas.drawPaint(paint);
            Log.d(Tag,"Before Text draw :"+Emp.id);

            canvas.drawText(Emp.name, (bitmap.getWidth()/2.f) , (bitmap.getHeight()/2.f), paint);
            canvas.drawText(" "+Emp.id, (bitmap.getWidth()/2.f) , (bitmap.getHeight()/2.f)+150, paint);



            wallpaperManager.setBitmap(bitmap);
            Log.d(Tag,"WallpaperSet.......................... ");

        } catch (InterruptedException | IOException e) {
            try {
                Log.d(Tag,"Not set Wallpaper. ");
                Thread.sleep(3000);
                for(int i=0;i<5;i++) {

//                    Toast.makeText(this, "test... :"+i , Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
                Log.d(Tag,"Not set Wallpaper. ");

            }
            e.printStackTrace();
            Log.d(Tag,"Not set Wallpaper. ");
            Log.d(Tag,"Error"+e.getMessage());

        }


    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(Tag,"JOB SE : job may be restarted.");
        flag=true;
        return true;
    }
}
