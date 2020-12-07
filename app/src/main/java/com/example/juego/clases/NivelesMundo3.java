package com.example.juego.clases;

import java.util.ArrayList;
import java.util.List;

public class NivelesMundo3 {
    private List<Grupo> grupos;
    public NivelesMundo3(int n){
        this.grupos = Seleccionador(n);
    }
    public List<Grupo> Seleccionador(int n){
        List<Grupo> nivel = new ArrayList<>();
        switch (n){
            case 1:
                nivel = nivel1();
                break;
            case 2:
                nivel = nivel2();
                break;
            case 3:
                nivel = nivel3();
                break;
            case 4:
                nivel = nivel4();
                break;
            case 5:
                nivel = nivel5();
                break;
            default:
                grupos = new ArrayList<>();
                break;
        }
        return nivel;
    }
    public List<Grupo> getNivelesMundo3(){
        return this.grupos;
    }

    public List<Grupo> nivel1(){
        List<Grupo> Level = new ArrayList<>();
        Level.add(new Grupo(0,0,0,0,0));
        Level.add(new Grupo(0,0,1,0,1));
        Level.add(new Grupo(0,0,2,0,2));
        Level.add(new Grupo(0,0,3,0,3));
        Level.add(new Grupo(0,0,4,0,4));
        Level.add(new Grupo(0,1,0,0,5));
        Level.add(new Grupo(1,1,1,5,6));
        Level.add(new Grupo(2,1,2,3,7));
        Level.add(new Grupo(2,1,3,1,8));
        Level.add(new Grupo(0,1,4,0,9));
        Level.add(new Grupo(0,2,0,0,10));
        Level.add(new Grupo(1,2,1,4,11));
        Level.add(new Grupo(1,2,2,2,12));
        Level.add(new Grupo(2,2,3,6,13));
        Level.add(new Grupo(0,2,4,0,14));
        Level.add(new Grupo(0,3,0,0,15));
        Level.add(new Grupo(1,3,1,3,16));
        Level.add(new Grupo(2,3,2,5,17));
        Level.add(new Grupo(1,3,3,4,18));
        Level.add(new Grupo(0,3,4,0,19));
        Level.add(new Grupo(0,4,0,0,20));
        Level.add(new Grupo(2,4,1,2,21));
        Level.add(new Grupo(1,4,2,3,22));
        Level.add(new Grupo(2,4,3,4,23));
        Level.add(new Grupo(0,4,4,0,24));
        Level.add(new Grupo(0,5,0,0,25));
        Level.add(new Grupo(1,5,1,3,26));
        Level.add(new Grupo(1,5,2,2,27));
        Level.add(new Grupo(2,5,3,3,28));
        Level.add(new Grupo(0,5,4,0,29));
        return Level;
    }
    public List<Grupo> nivel2(){
        List<Grupo> Level = new ArrayList<>();
        Level.add(new Grupo(1,0,0,3,0));
        Level.add(new Grupo(2,0,1,2,1));
        Level.add(new Grupo(2,0,2,1,2));
        Level.add(new Grupo(1,0,3,3,3));
        Level.add(new Grupo(2,0,4,3,4));
        Level.add(new Grupo(1,1,0,1,5));
        Level.add(new Grupo(1,1,1,5,6));
        Level.add(new Grupo(2,1,2,4,7));
        Level.add(new Grupo(2,1,3,3,8));
        Level.add(new Grupo(1,1,4,6,9));
        Level.add(new Grupo(1,2,0,2,10));
        Level.add(new Grupo(2,2,1,2,11));
        Level.add(new Grupo(0,2,2,0,12));
        Level.add(new Grupo(1,2,3,6,13));
        Level.add(new Grupo(2,2,4,3,14));
        Level.add(new Grupo(1,3,0,2,15));
        Level.add(new Grupo(2,3,1,4,16));
        Level.add(new Grupo(0,3,2,0,17));
        Level.add(new Grupo(1,3,3,4,18));
        Level.add(new Grupo(1,3,4,1,19));
        Level.add(new Grupo(2,4,0,2,20));
        Level.add(new Grupo(1,4,1,2,21));
        Level.add(new Grupo(0,4,2,0,22));
        Level.add(new Grupo(1,4,3,3,23));
        Level.add(new Grupo(2,4,4,2,24));
        Level.add(new Grupo(0,5,0,0,25));
        Level.add(new Grupo(2,5,1,3,26));
        Level.add(new Grupo(1,5,2,1,27));
        Level.add(new Grupo(2,5,3,2,28));
        Level.add(new Grupo(0,5,4,0,29));
        return Level;
    }
    public List<Grupo> nivel3(){
        List<Grupo> Level = new ArrayList<>();
        Level.add(new Grupo(1,0,0,2,0));
        Level.add(new Grupo(1,0,1,3,1));
        Level.add(new Grupo(2,0,2,3,2));
        Level.add(new Grupo(1,0,3,4,3));
        Level.add(new Grupo(2,0,4,2,4));
        Level.add(new Grupo(2,1,0,2,5));
        Level.add(new Grupo(1,1,1,4,6));
        Level.add(new Grupo(2,1,2,5,7));
        Level.add(new Grupo(2,1,3,3,8));
        Level.add(new Grupo(1,1,4,5,9));
        Level.add(new Grupo(1,2,0,4,10));
        Level.add(new Grupo(1,2,1,6,11));
        Level.add(new Grupo(2,2,2,3,12));
        Level.add(new Grupo(1,2,3,1,13));
        Level.add(new Grupo(1,2,4,3,14));
        Level.add(new Grupo(2,3,0,2,15));
        Level.add(new Grupo(1,3,1,2,16));
        Level.add(new Grupo(1,3,2,4,17));
        Level.add(new Grupo(2,3,3,3,18));
        Level.add(new Grupo(1,3,4,2,19));
        Level.add(new Grupo(0,4,0,0,20));
        Level.add(new Grupo(2,4,1,3,21));
        Level.add(new Grupo(1,4,2,3,22));
        Level.add(new Grupo(1,4,3,3,23));
        Level.add(new Grupo(0,4,4,0,24));
        Level.add(new Grupo(2,5,0,3,25));
        Level.add(new Grupo(1,5,1,4,26));
        Level.add(new Grupo(0,5,2,0,27));
        Level.add(new Grupo(1,5,3,4,28));
        Level.add(new Grupo(1,5,4,4,29));
        return Level;
    }
    public List<Grupo> nivel4(){
        List<Grupo> Level = new ArrayList<>();
        Level.add(new Grupo(0,0,0,0,0));
        Level.add(new Grupo(0,0,1,0,1));
        Level.add(new Grupo(0,0,2,0,2));
        Level.add(new Grupo(1,0,3,4,3));
        Level.add(new Grupo(0,0,4,0,4));
        Level.add(new Grupo(0,1,0,0,5));
        Level.add(new Grupo(0,1,1,0,6));
        Level.add(new Grupo(1,1,2,3,7));
        Level.add(new Grupo(2,1,3,3,8));
        Level.add(new Grupo(0,1,4,0,9));
        Level.add(new Grupo(0,2,0,0,10));
        Level.add(new Grupo(1,2,1,3,11));
        Level.add(new Grupo(0,2,2,0,12));
        Level.add(new Grupo(1,2,3,4,13));
        Level.add(new Grupo(0,2,4,0,14));
        Level.add(new Grupo(2,3,0,2,15));
        Level.add(new Grupo(2,3,1,2,16));
        Level.add(new Grupo(1,3,2,3,17));
        Level.add(new Grupo(1,3,3,4,18));
        Level.add(new Grupo(2,3,4,2,19));
        Level.add(new Grupo(0,4,0,0,20));
        Level.add(new Grupo(0,4,1,0,21));
        Level.add(new Grupo(0,4,2,0,22));
        Level.add(new Grupo(2,4,3,4,23));
        Level.add(new Grupo(0,4,4,0,24));
        Level.add(new Grupo(0,5,0,0,25));
        Level.add(new Grupo(0,5,1,0,26));
        Level.add(new Grupo(0,5,2,0,27));
        Level.add(new Grupo(1,5,3,5,28));
        Level.add(new Grupo(0,5,4,0,29));
        return Level;
    }
    public List<Grupo> nivel5(){
        List<Grupo> Level = new ArrayList<>();
        Level.add(new Grupo(1,0,0,4,0));
        Level.add(new Grupo(1,0,1,1,1));
        Level.add(new Grupo(2,0,2,3,2));
        Level.add(new Grupo(2,0,3,3,3));
        Level.add(new Grupo(1,0,4,4,4));
        Level.add(new Grupo(2,1,0,3,5));
        Level.add(new Grupo(2,1,1,1,6));
        Level.add(new Grupo(1,1,2,6,7));
        Level.add(new Grupo(1,1,3,3,8));
        Level.add(new Grupo(2,1,4,3,9));
        Level.add(new Grupo(2,2,0,1,10));
        Level.add(new Grupo(1,2,1,2,11));
        Level.add(new Grupo(1,2,2,5,12));
        Level.add(new Grupo(2,2,3,4,13));
        Level.add(new Grupo(1,2,4,3,14));
        Level.add(new Grupo(0,3,0,0,15));
        Level.add(new Grupo(2,3,1,2,16));
        Level.add(new Grupo(1,3,2,4,17));
        Level.add(new Grupo(2,3,3,3,18));
        Level.add(new Grupo(0,3,4,0,19));
        Level.add(new Grupo(0,4,0,0,20));
        Level.add(new Grupo(1,4,1,6,21));
        Level.add(new Grupo(2,4,2,4,22));
        Level.add(new Grupo(1,4,3,3,23));
        Level.add(new Grupo(0,4,4,0,24));
        Level.add(new Grupo(0,5,0,0,25));
        Level.add(new Grupo(0,5,1,0,26));
        Level.add(new Grupo(1,5,2,1,27));
        Level.add(new Grupo(0,5,3,0,28));
        Level.add(new Grupo(0,5,4,0,29));
        return Level;
    }
}
