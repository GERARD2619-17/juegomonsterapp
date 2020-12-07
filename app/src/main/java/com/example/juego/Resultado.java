package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    private String resultado;
    private LinearLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        this.resultado = getIntent().getStringExtra("resultado");
        ln = findViewById(R.id.idResultado);
        if(Integer.parseInt(resultado)==1){
            //Ganador
            ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.ganaste));
        }else if(Integer.parseInt(resultado)==2){
            //Perdedor
            ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.perdiste));
        }else {
            //Rendicion
            ln.setBackgroundDrawable(getResources().getDrawable(R.drawable.rendido));
        }
    }
    public void fin(View v){
        finish();
    }
}