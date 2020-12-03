package com.example.juego.clases;

public class Partida {
    private String id;
    private String movimiento;
    private String atacado;
    private String atacante;

    public Partida() {
    }

    public Partida(String id, String movimiento, String atacado, String atacante) {
        this.id = id;
        this.movimiento = movimiento;
        this.atacado = atacado;
        this.atacante = atacante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getAtacado() {
        return atacado;
    }

    public void setAtacado(String atacado) {
        this.atacado = atacado;
    }

    public String getAtacante() {
        return atacante;
    }

    public void setAtacante(String atacante) {
        this.atacante = atacante;
    }
}
