package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView titulo;
    private Typeface Letra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fuente = "fuentes/Zombies3.ttf";
        this.Letra = Typeface.createFromAsset(getAssets(),fuente);
        titulo = (TextView) findViewById(R.id.txtMenuPrincipal);
        titulo.setTypeface(Letra);
    }
    //multijugador
    public void MULTIJUGADOR_onClick(View v){
        Intent multijugador = new Intent(this, PantallaMenuDos.class);
        startActivity(multijugador);
    }
    //Aventura
    public void AVENTURA_onClick(View v){
        Intent aventura = new Intent(this, SelectorMundos.class);
        startActivity(aventura);
    }
    public void CONFIGURACION_onClick(View v){
        Intent configuracion = new Intent(this, Configuracion.class);
        startActivity(configuracion);
    }
   /* @Override
    public void onPause() {
        super.onPause();
        //pausar();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action",AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }*/
}