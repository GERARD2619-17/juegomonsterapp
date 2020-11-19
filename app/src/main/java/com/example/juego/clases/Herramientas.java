package com.example.juego.clases;

import android.os.CountDownTimer;
import android.os.Handler;

public class Herramientas {
    public static Handler Correr = new Handler();
    public static void pausa(){
        new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
                //Toast.makeText(Mundo1Niveles.this, "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
            }
            public void onFinish() {

            }
        }.start();
    }
    public static boolean porcentaje(int n){
        boolean retorno;
        int si = n;
        int[] PORCENTAJE = new int[100];
        for(int i=0; i<si;i++) PORCENTAJE[i] = 1;
        for (int i=si;i<100;i++) PORCENTAJE[i] = 0;
        int cantidad = (int)(Math.random() * 99);
        if(PORCENTAJE[cantidad]==1){
            retorno = true;
        }
        else {
            retorno = false;
        }
        return retorno;
    }
}
