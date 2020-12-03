package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SelectorMundos extends AppCompatActivity {
    private ImageButton selector1, selector2, selector3;
    private int selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_mundos);
        this.selector = 0;
        this.selector1 = findViewById(R.id.btn_selector1);
        this.selector2 = findViewById(R.id.btn_selector2);
        this.selector3 = findViewById(R.id.btn_selector3);
    }
    public void Izquierda_onClick(View v){
        if(selector==0){
            selector=3;
            selector1.setImageResource(R.drawable.img_mundo1);
            selector2.setImageResource(R.drawable.img_mundo2);
            selector3.setImageResource(R.drawable.img_tutorial);
        }else if(selector==3){
            selector=2;
            selector1.setImageResource(R.drawable.img_tutorial);
            selector2.setImageResource(R.drawable.img_mundo1);
            selector3.setImageResource(R.drawable.img_mundo2);
        }else {
            selector=0;
            selector1.setImageResource(R.drawable.img_mundo2);
            selector2.setImageResource(R.drawable.img_tutorial);
            selector3.setImageResource(R.drawable.img_mundo1);
        }
    }
    public void Derecha_onClick(View v){
        if(selector==0){
            selector=1;
            selector1.setImageResource(R.drawable.img_tutorial);
            selector2.setImageResource(R.drawable.img_mundo1);
            selector3.setImageResource(R.drawable.img_mundo2);
        }else if(selector==1){
            selector=2;
            selector1.setImageResource(R.drawable.img_mundo1);
            selector2.setImageResource(R.drawable.img_mundo2);
            selector3.setImageResource(R.drawable.img_tutorial);
        }else {
            selector=0;
            selector1.setImageResource(R.drawable.img_mundo2);
            selector2.setImageResource(R.drawable.img_tutorial);
            selector3.setImageResource(R.drawable.img_mundo1);
        }
    }
    public void Centro_onClick(View v){
        if(selector==0){
            Intent tutorial = new Intent(this, tutorial.class);
            startActivity(tutorial);
        }else if(selector==1){
            Intent aventura = new Intent(this, Mundo1.class);
            startActivity(aventura);
        }else {
            Toast.makeText(this, "El mundo 2 aun no está listo", Toast.LENGTH_SHORT).show();
        }
    }
    public void finish(View v){
        finish();
    }
}