package com.example.juego.clases;

public class Jugador {
    String id;
    String Nombre;
    String Estado;
    String TurnoInicial;

    public Jugador() {
    }

    public Jugador(String id, String nombre, String estado, String turnoInicial) {
        this.id = id;
        this.Nombre = nombre;
        this.Estado = estado;
        this.TurnoInicial = turnoInicial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTurnoInicial() {
        return TurnoInicial;
    }

    public void setTurnoInicial(String turnoInicial) {
        TurnoInicial = turnoInicial;
    }
}
