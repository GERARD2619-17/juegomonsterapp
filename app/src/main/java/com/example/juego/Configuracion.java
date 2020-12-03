package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{
    private TextView tituloconfiguracion,txtVolumen;
    private Typeface Letraconfiguracion,LetraVolumen;

    ImageButton btnpantallaAtras;
    ToggleButton play_pause;
    AudioManager audioManager;
    SeekBar sk_volumen;
    private SharedPreferences Desbloqueados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        String fuente = "fuentes/shlop rg.ttf";
        this.Letraconfiguracion = Typeface.createFromAsset(getAssets(),fuente);
        this.LetraVolumen = Typeface.createFromAsset(getAssets(),fuente);
        tituloconfiguracion = (TextView) findViewById(R.id.txtsonido);
        tituloconfiguracion.setTypeface(Letraconfiguracion);
        txtVolumen = (TextView) findViewById(R.id.txtvolumen);
        txtVolumen.setTypeface(LetraVolumen);

        this.Desbloqueados = getSharedPreferences(Mundo1.ARCHIVO,MODE_PRIVATE);

        play_pause = findViewById(R.id.play_pause);
        play_pause.setOnClickListener(this);
        btnpantallaAtras = findViewById(R.id.btnpantallaAtras);
        btnpantallaAtras.setOnClickListener(this);
        Volumen();
    }
    private void Volumen(){
        try {
            sk_volumen = (SeekBar)findViewById(R.id.volumeSeekbar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            sk_volumen.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            sk_volumen.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            sk_volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override            public void onStopTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void borrarDatos_onClick(View v){
        if(this.Desbloqueados!=null){
            SharedPreferences.Editor editor = this.Desbloqueados.edit();
            editor.putString(Mundo1.KEY,"");
            if(editor.commit()){
                Toast.makeText(this, "Los niveles se reiniciaron en 1", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.play_pause:{
                Intent pantalla1 = new Intent(this, MainActivity.class);
                Intent pantalla2 = new Intent(this, online.class);
                Intent pantalla3 = new Intent(this, Mundo1.class);
                Intent pantalla4 = new Intent(this, Mundo1Niveles.class);
                Intent pantalla5 = new Intent(this, PantallaMenuDos.class);
                Intent pantalla6 = new Intent(this, SelectorMundos.class);
                Intent i = new Intent(this, AudioService.class);
                i.putExtra("action", AudioService.START);
                Intent pa = new Intent(this, AudioService.class);
                pa.putExtra("action", AudioService.PAUSE);
                startService(pa);
                startService(i);
                startService(pantalla1);
                startService(pantalla2);
                startService(pantalla3);
                startService(pantalla4);
                startService(pantalla5);
                startService(pantalla6);

            }break;
            case R.id.btnpantallaAtras:{

                Intent i = new Intent(this, MainActivity.class);
               /* Intent e = new Intent(this, AudioService.class);
                e.putExtra("action", AudioService.START);*/

                //  startService(e);
                startActivity(i);

            }break;

//Hola este es un comentario

        }
    }
}