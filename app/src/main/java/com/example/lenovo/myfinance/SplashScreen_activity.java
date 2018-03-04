package com.example.lenovo.myfinance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 1/7/2018.
 */

public class SplashScreen_activity extends AppCompatActivity{
    LinearLayout l1,l2,l3;
    ImageView logo;
    Animation uptodown;
    Animation downtoup;
    Animation fadein;
    Animation rotate;
    Animation expandin;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int SPLASH_TIME_OUT = 2800;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        logo =(ImageView) findViewById(R.id.logo);
        l1 = (LinearLayout) findViewById(R.id.l1);
        l2 = (LinearLayout) findViewById(R.id.l2);
        l3=(LinearLayout)findViewById(R.id.money_back);

        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        l1.setAnimation(fadein);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        l3.setAnimation(uptodown);
        rotate =AnimationUtils.loadAnimation(this,R.anim.rotate);
        logo.setAnimation(rotate);
        downtoup =AnimationUtils.loadAnimation(this,R.anim.downtoup);
        l2.setAnimation(downtoup);



        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen_activity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    }

