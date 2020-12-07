package com.example.juego;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juego.clases.Grupo;
import com.example.juego.clases.Jugador;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class online extends AppCompatActivity {

    private TextView titulo;
    private EditText texto;
    private Typeface Letra;
    private Typeface Letra2;
    private List<Jugador> jugador;
    private int pos=0;
    private Button txtIdNick;
    private TextView txtBuscando;
    private String llave;
    private String llavetemporal;

    private Handler Correr;
    public boolean continuar;

    private FirebaseDatabase database;
    private DatabaseReference databaseJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        String fuente = "fuentes/Zombies3.ttf";
        String fuente2 = "fuentes/Momias.otf";
        this.Letra = Typeface.createFromAsset(getAssets(),fuente);
        this.Letra2 = Typeface.createFromAsset(getAssets(),fuente2);
        titulo = (TextView) findViewById(R.id.txtNickName);
        txtBuscando = (TextView) findViewById(R.id.txtBuscando);
        txtIdNick = (Button) findViewById(R.id.txtIdNick);
        texto = findViewById(R.id.edit);
        titulo.setTypeface(Letra);
        txtBuscando.setTypeface(Letra);
        texto.setTypeface(Letra);
        txtIdNick.setTypeface(Letra2);
        llavetemporal = "";
        llave="";

        this.Correr = new Handler();
        jugador = new ArrayList<>();
        continuar=false;

        this.database = FirebaseDatabase.getInstance();
        this.databaseJugador = database.getReference("jugador");

        databaseJugador.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Jugador j = snapshot.getValue(Jugador.class);
                Jugador J=new Jugador(j.getId(),j.getNombre(),j.getEstado(),j.getTurnoInicial());
                jugador.add(J);
                if(j.getEstado().equals("jugando") && verificar()){
                    //Habre el juego con la key de los dos jugadores
                    abrir(jugador.get(pos));

                    databaseJugador.child(jugador.get(pos).getId()).removeValue();
                    databaseJugador.child(jugador.get(jugador.size()-1).getId()).removeValue();
                    pos = 0;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void abrir(Jugador j){
        Intent multijugador = new Intent(this, Multijugador.class);
        multijugador.putExtra("key",j.getId()+jugador.get(jugador.size()-1).getId());
        multijugador.putExtra("rival",jugador.get(jugador.size()-1).getNombre());
        multijugador.putExtra("turno",j.getTurnoInicial());
        multijugador.putExtra("yo",texto.getText().toString());
        startActivity(multijugador);
        finish();
    }
    public void Buscar_onClick(View v){
        if(continuar){
            continuar=false;
            txtIdNick.setText("Buscar");
            txtIdNick.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button4));
            databaseJugador.child(llave).removeValue();
            jugador.remove(jugador.size()-1);
            llave="";
        }else {
            if (texto.getText().toString().length() == 0) {
                Toast.makeText(this, "Ingrese su Nombre", Toast.LENGTH_SHORT).show();
            }else{
            int contador = 0;
            boolean seguir = true;
            do {
                if (contador >= jugador.size()) {
                    continuar = true;
                    String key = databaseJugador.push().getKey();
                    Jugador j = new Jugador(key, texto.getText().toString(), "buscando", "1");//1 - inserte player1
                    databaseJugador.child(key).setValue(j);
                    llave = key;
                    txtIdNick.setText("Cancelar");
                    txtIdNick.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_button5));
                    new Thread(new cargar()).start();
                    seguir = false;
                } else {
                    if (jugador.get(contador).getEstado().equals("buscando")) {
                        //Emparejo con el jugador que busca partida
                        String key = databaseJugador.push().getKey();
                        Jugador j = new Jugador(key, texto.getText().toString(), "jugando", "2");//yo
                        databaseJugador.child(key).setValue(j);
                        llave = key;
                        //Actualizo el jugador de estado "buscando" a "jugando"
                        pos = contador;
                        seguir = false;

                        //Habre el juego con la key de los dos jugadores
                        Intent multijugador = new Intent(this, Multijugador.class);
                        multijugador.putExtra("key", jugador.get(contador).getId() + key);
                        multijugador.putExtra("rival", jugador.get(contador).getNombre());
                        multijugador.putExtra("turno", j.getTurnoInicial());
                        multijugador.putExtra("yo", texto.getText().toString());
                        startActivity(multijugador);
                        for (int i = 0; i <= jugador.size(); i++) {
                            jugador.remove(i);
                        }
                        finish();

                    }
                    contador++;
                }
            } while (seguir);
        }
        }
    }

    private void BorrarBase(){
        for(int i=0;i<jugador.size();i++){
            databaseJugador.child(jugador.get(i).getId()).removeValue();
        }
    }

    public boolean verificar(){
        return continuar;
    }
    final class cargar implements Runnable{
        @Override
        public void run() {
            while(verificar()){
                metodoEspera();
                Correr.post(new Runnable() {
                    @Override
                    public void run() {
                        if(continuar){
                            if(txtBuscando.getText().toString().equals("") || txtBuscando.getText().toString().equals("Cargando...")){
                                txtBuscando.setText("Cargando.");
                            }else if(txtBuscando.getText().toString().equals("Cargando.")){
                                txtBuscando.setText("Cargando..");
                            }else {
                                txtBuscando.setText("Cargando...");
                            }
                        }else {
                            txtBuscando.setText("");
                        }

                    }

                });

            }

        }
        private void metodoEspera() {
            try{
                Thread.sleep(1000);
            }catch (Exception e){}
        }

    }

    public void fin(View v){
        try {
            databaseJugador.child(llave).removeValue();
            jugador.remove(jugador.size()-1);
            llave="";
        }
        catch (Exception e){

        }
        finish();
    }
}