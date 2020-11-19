package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;
//Mundo1
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.juego.clases.Grupo;

import java.util.ArrayList;
import java.util.List;

public class Mundo1 extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences Desbloqueados;
    public static final String ARCHIVO = "archivo";
    public static final String KEY = "llave";
    private int nivelesPasados;
    private List<ImageButton> botones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundo1);
        this.Desbloqueados = getSharedPreferences(ARCHIVO,MODE_PRIVATE);
        agregarBotones();
        cargarSharedPreferences();
        habilitarNiveles();
    }
    public void Atras_onClick(View v){
        //Intent atras = new Intent(this, MainActivity.class);
        //startActivity(atras);
        finish();
    }
    private void agregarBotones(){
        ImageButton btn1 = findViewById(R.id.m1_btn1);
        botones.add(btn1);
        ImageButton btn2 = findViewById(R.id.m1_btn2);
        botones.add(btn2);
        ImageButton btn3 = findViewById(R.id.m1_btn3);
        botones.add(btn3);
        ImageButton btn4 = findViewById(R.id.m1_btn4);
        botones.add(btn4);
        ImageButton btn5 = findViewById(R.id.m1_btn5);
        botones.add(btn5);
        ImageButton btn6 = findViewById(R.id.m1_btn6);
        botones.add(btn6);
        ImageButton btn7 = findViewById(R.id.m1_btn7);
        botones.add(btn7);
        ImageButton btn8 = findViewById(R.id.m1_btn8);
        botones.add(btn8);
        ImageButton btn9 = findViewById(R.id.m1_btn9);
        botones.add(btn9);
        ImageButton btn10 = findViewById(R.id.m1_btn10);
        botones.add(btn10);
        ImageButton btn11 = findViewById(R.id.m1_btn11);
        botones.add(btn11);
        ImageButton btn12 = findViewById(R.id.m1_btn12);
        botones.add(btn12);
        ImageButton btn13 = findViewById(R.id.m1_btn13);
        botones.add(btn13);
        ImageButton btn14 = findViewById(R.id.m1_btn14);
        botones.add(btn14);
        ImageButton btn15 = findViewById(R.id.m1_btn15);
        botones.add(btn15);
        ImageButton btn16 = findViewById(R.id.m1_btn16);
        botones.add(btn16);
        ImageButton btn17 = findViewById(R.id.m1_btn17);
        botones.add(btn17);
        ImageButton btn18 = findViewById(R.id.m1_btn18);
        botones.add(btn18);
        ImageButton btn19 = findViewById(R.id.m1_btn19);
        botones.add(btn19);
        ImageButton btn20 = findViewById(R.id.m1_btn20);
        botones.add(btn20);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
        btn12.setOnClickListener(this);
        btn13.setOnClickListener(this);
        btn14.setOnClickListener(this);
        btn15.setOnClickListener(this);
        btn16.setOnClickListener(this);
        btn17.setOnClickListener(this);
        btn18.setOnClickListener(this);
        btn19.setOnClickListener(this);
        btn20.setOnClickListener(this);
    }

    private void cargarSharedPreferences(){
        String niveles_superados;
        if(this.Desbloqueados!=null){
            niveles_superados = Desbloqueados.getString(KEY,"");
            if(niveles_superados.length()==0){
                SharedPreferences.Editor editor = this.Desbloqueados.edit();
                editor.putString(KEY,"0");
                if(editor.commit()){
                    Log.d("TAG","Informacion guardada");
                }else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }
        this.nivelesPasados = Integer.parseInt(Desbloqueados.getString(KEY,""));
    }

    private void habilitarNiveles(){
        List<Integer> enteros = new ArrayList<>();
        enteros.add(R.drawable.m1_btn1);
        enteros.add(R.drawable.m1_btn2);
        enteros.add(R.drawable.m1_btn3);
        enteros.add(R.drawable.m1_btn4);
        enteros.add(R.drawable.m1_btn5);
        enteros.add(R.drawable.m1_btn6);
        enteros.add(R.drawable.m1_btn7);
        enteros.add(R.drawable.m1_btn8);
        enteros.add(R.drawable.m1_btn9);
        enteros.add(R.drawable.m1_btn10);
        enteros.add(R.drawable.m1_btn11);
        enteros.add(R.drawable.m1_btn12);
        enteros.add(R.drawable.m1_btn13);
        enteros.add(R.drawable.m1_btn14);
        enteros.add(R.drawable.m1_btn15);
        enteros.add(R.drawable.m1_btn16);
        enteros.add(R.drawable.m1_btn17);
        enteros.add(R.drawable.m1_btn18);
        enteros.add(R.drawable.m1_btn19);
        enteros.add(R.drawable.m1_btn20);
        for(int i=1;i<=nivelesPasados;i++){
            botones.get(i).setImageResource(enteros.get(i));
        }
    }

    private boolean Revisar(int n){
        boolean verificar=false;
        if(n<=nivelesPasados+1){
            verificar=true;
        }
        return verificar;
    }
    @Override
    public void onClick(View view) {
            String nivelSeleccionado="";
            switch (view.getId()) {
                case R.id.m1_btn1:
                    nivelSeleccionado="1";
                    break;
                case R.id.m1_btn2:
                    nivelSeleccionado="2";
                    break;
                case R.id.m1_btn3:
                    nivelSeleccionado="3";
                    break;
                case R.id.m1_btn4:
                    nivelSeleccionado="4";
                    break;
                case R.id.m1_btn5:
                    nivelSeleccionado="5";
                    break;
                case R.id.m1_btn6:
                    nivelSeleccionado="6";
                    break;
                case R.id.m1_btn7:
                    nivelSeleccionado="7";
                    break;
                case R.id.m1_btn8:
                    nivelSeleccionado="8";
                    break;
                case R.id.m1_btn9:
                    nivelSeleccionado="9";
                    break;
                case R.id.m1_btn10:
                    nivelSeleccionado="10";
                    break;
                case R.id.m1_btn11:
                    nivelSeleccionado="11";
                    break;
                case R.id.m1_btn12:
                    nivelSeleccionado="12";
                    break;
                case R.id.m1_btn13:
                    nivelSeleccionado="13";
                    break;
                case R.id.m1_btn14:
                    nivelSeleccionado="14";
                    break;
                case R.id.m1_btn15:
                    nivelSeleccionado="15";
                    break;
                case R.id.m1_btn16:
                    nivelSeleccionado="16";
                    break;
                case R.id.m1_btn17:
                    nivelSeleccionado="17";
                    break;
                case R.id.m1_btn18:
                    nivelSeleccionado="18";
                    break;
                case R.id.m1_btn19:
                    nivelSeleccionado="19";
                    break;
                case R.id.m1_btn20:
                    nivelSeleccionado="20";
                    break;
                default:
                    nivelSeleccionado="";
                    break;
            }
            if(Revisar(Integer.parseInt(nivelSeleccionado))){
                Intent mundo1 = new Intent(this, Mundo1Niveles.class);
                mundo1.putExtra("nivel",nivelSeleccionado);
                startActivity(mundo1);
            }
    }
    @Override
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
        cargarSharedPreferences();
        habilitarNiveles();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }
}