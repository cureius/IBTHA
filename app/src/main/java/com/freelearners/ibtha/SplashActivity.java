package com.freelearners.ibtha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.freelearners.ibtha.identification.IdentificationActivity;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim , bottomAnim;
    ImageView logo , name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        hooks
        logo=findViewById(R.id.sp_logo);
        name=findViewById(R.id.sp_app_name);
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom);
        topAnim.setDuration(2000);
        bottomAnim.setDuration(1500);
//        animation
        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);

        int SPLASH_SCREEN = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this , IdentificationActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}