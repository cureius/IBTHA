package com.freelearners.ibtha;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.freelearners.ibtha.home.MainActivity;
import com.freelearners.ibtha.onBoarding.IntroActivity;

public class SplashActivity extends AppCompatActivity {

    Animation topAnim , bottomAnim;
    ImageView logo , name;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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


        int SPLASH_SCREEN = 3000;
        new Handler().postDelayed(() -> {
            SharedPreferences getSharedPreferences = getSharedPreferences("identification", MODE_PRIVATE);
            boolean loggedIn = getSharedPreferences.getBoolean("identified", false);


            if (loggedIn){
                Toast.makeText(SplashActivity.this, "loggedIn", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SplashActivity.this , MainActivity.class);
                startActivity(intent);

            }else {
                Intent intent = new Intent(SplashActivity.this , IntroActivity.class);
                startActivity(intent);
            }
            finish();

        }, SPLASH_SCREEN);


    }
}