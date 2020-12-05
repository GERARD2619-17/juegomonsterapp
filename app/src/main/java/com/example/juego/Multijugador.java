package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Multijugador extends AppCompatActivity {

    private String keis, rival, turno;

    private FirebaseDatabase database;
    private DatabaseReference databasePartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multijugador);
        this.keis = getIntent().getStringExtra("key");
        this.rival = getIntent().getStringExtra("rival");
        this.turno = getIntent().getStringExtra("turno");

        this.database = FirebaseDatabase.getInstance();
        this.databasePartida = database.getReference(keis);
    }
}