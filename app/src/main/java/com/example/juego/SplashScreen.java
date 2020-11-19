package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.juego.clases.ProgressBarAnimation;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progrees_bar);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        ProgreesAnimation();
    }
    public void ProgreesAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation( this, progressBar, 0f, 100f);
        anim.setDuration(4000);
        progressBar.setAnimation(anim);
    }
}