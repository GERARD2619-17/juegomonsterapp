package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

//menu2
public class PantallaMenuDos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_menu_dos);
    }
    //botones
    public void ordenador_onClick(View v){
        Intent mundo1 = new Intent(this, Mundo1Niveles.class);
        mundo1.putExtra("nivel","15");
        startActivity(mundo1);
    }
    public void local_onClick(View v){
        Toast.makeText(this, "Esta opcion aun no esta programada, prueba el juego en linea", Toast.LENGTH_SHORT).show();
    }
    public void online_onClick(View v){
        Intent o = new Intent(this,online.class);
        startActivity(o);
    }
    public void Retroceder_onClick(View v){
        finish();
    }

}