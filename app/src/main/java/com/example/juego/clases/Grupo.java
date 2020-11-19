package com.example.juego.clases;

public class Grupo {
    //Id el identificador del equipo: 1 = equipo a, 2 = equipo b, 0 = celda nula
    private int id;
    //Posicion en x del tablero
    private int posX;
    //Posicion en y del tablero
    private int posY;
    //Cantidad de piezas en cada cuadro del tablero
    private int cantidad;
    //Numero que tiene en el tablero ej: (1,1) = 1, (1,2) = 2, etc.
    private int numero;
    //Si es el atacado, el que ataca u otra celda: 0 No juega, 1 Celda bajo ataque, 2 Celda atacante
    private int estado;

    public Grupo(int I, int X, int Y, int C, int N){
        this.id = I;
        this.posX = X;
        this.posY = Y;
        this.cantidad = C;
        this.numero = N;
        this.estado = 0;
    }

    public Grupo(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEstado() { return estado; }

    public void setEstado(int estado) { this.estado = estado; }

}