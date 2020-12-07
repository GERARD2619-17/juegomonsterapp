package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juego.clases.Grupo;
import com.example.juego.clases.Herramientas;
import com.example.juego.clases.NivelesMundo1;

import java.util.ArrayList;
import java.util.List;

public class Mundo1Niveles extends AppCompatActivity implements View.OnClickListener{
    private List<Grupo> grupos = new ArrayList<>();
    private List<ImageButton> botones = new ArrayList<>();
    private int turno=1;
    private int NumeroEnJuego=0;
    private static int contador=0;
    private Handler Correr;
    private boolean incrementando=false;
    private int Nivel;
    private int N;
    private int accion=0;
    private boolean fin;
    private SharedPreferences Desbloqueados;
    public boolean jugando;
    private TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundo1_niveles);
        this.Desbloqueados = getSharedPreferences(Mundo1.ARCHIVO,MODE_PRIVATE);
        fin=false;
        this.jugando = true;
        this.Correr = new Handler();
        if(getIntent().getStringExtra("nivel")!=null){
            Nivel = Integer.parseInt(getIntent().getStringExtra("nivel"));
        }
        else {
            Nivel=0;
        }
        this.titulo = findViewById(R.id.txtTituloNivel);
        titulo.setText("Nivel "+Nivel);
        N=0;
        agregarBotones();
        cargarGrupos();
        cargar();
    }
    //Carga e inicaliza los botones, en un metodo y luego los agrega en una lista "botones" para acceder a ellos cuando querramos
    private void agregarBotones(){
        ImageButton btn1 = findViewById(R.id.btn1);
        botones.add(btn1);
        ImageButton btn2 = findViewById(R.id.btn2);
        botones.add(btn2);
        ImageButton btn3 = findViewById(R.id.btn3);
        botones.add(btn3);
        ImageButton btn4 = findViewById(R.id.btn4);
        botones.add(btn4);
        ImageButton btn5 = findViewById(R.id.btn5);
        botones.add(btn5);
        ImageButton btn6 = findViewById(R.id.btn6);
        botones.add(btn6);
        ImageButton btn7 = findViewById(R.id.btn7);
        botones.add(btn7);
        ImageButton btn8 = findViewById(R.id.btn8);
        botones.add(btn8);
        ImageButton btn9 = findViewById(R.id.btn9);
        botones.add(btn9);
        ImageButton btn10 = findViewById(R.id.btn10);
        botones.add(btn10);
        ImageButton btn11 = findViewById(R.id.btn11);
        botones.add(btn11);
        ImageButton btn12 = findViewById(R.id.btn12);
        botones.add(btn12);
        ImageButton btn13 = findViewById(R.id.btn13);
        botones.add(btn13);
        ImageButton btn14 = findViewById(R.id.btn14);
        botones.add(btn14);
        ImageButton btn15 = findViewById(R.id.btn15);
        botones.add(btn15);
        ImageButton btn16 = findViewById(R.id.btn16);
        botones.add(btn16);
        ImageButton btn17 = findViewById(R.id.btn17);
        botones.add(btn17);
        ImageButton btn18 = findViewById(R.id.btn18);
        botones.add(btn18);
        ImageButton btn19 = findViewById(R.id.btn19);
        botones.add(btn19);
        ImageButton btn20 = findViewById(R.id.btn20);
        botones.add(btn20);
        ImageButton btn21 = findViewById(R.id.btn21);
        botones.add(btn21);
        ImageButton btn22 = findViewById(R.id.btn22);
        botones.add(btn22);
        ImageButton btn23 = findViewById(R.id.btn23);
        botones.add(btn23);
        ImageButton btn24 = findViewById(R.id.btn24);
        botones.add(btn24);
        ImageButton btn25 = findViewById(R.id.btn25);
        botones.add(btn25);
        ImageButton btn26 = findViewById(R.id.btn26);
        botones.add(btn26);
        ImageButton btn27 = findViewById(R.id.btn27);
        botones.add(btn27);
        ImageButton btn28 = findViewById(R.id.btn28);
        botones.add(btn28);
        ImageButton btn29 = findViewById(R.id.btn29);
        botones.add(btn29);
        ImageButton btn30 = findViewById(R.id.btn30);
        botones.add(btn30);
        ImageButton btn31 = findViewById(R.id.btn31);
        botones.add(btn31);
        ImageButton btn32 = findViewById(R.id.btn32);
        botones.add(btn32);
        ImageButton btn33 = findViewById(R.id.btn33);
        botones.add(btn33);
        ImageButton btn34 = findViewById(R.id.btn34);
        botones.add(btn34);
        ImageButton btn35 = findViewById(R.id.btn35);
        botones.add(btn35);
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
        btn21.setOnClickListener(this);
        btn22.setOnClickListener(this);
        btn23.setOnClickListener(this);
        btn24.setOnClickListener(this);
        btn25.setOnClickListener(this);
        btn26.setOnClickListener(this);
        btn27.setOnClickListener(this);
        btn28.setOnClickListener(this);
        btn29.setOnClickListener(this);
        btn30.setOnClickListener(this);
        btn31.setOnClickListener(this);
        btn32.setOnClickListener(this);
        btn33.setOnClickListener(this);
        btn34.setOnClickListener(this);
        btn35.setOnClickListener(this);
    }
    //Genera las piezas en el tablero y los agrega en la lista "grupos" para acceder a ellos cuando querramos
    private void cargarGrupos(){
        NivelesMundo1 nivel = new NivelesMundo1(Nivel);
        grupos = nivel.getNivelesMundo1();
    }
    //Carga e inicaliza los botones, en un metodo y luego los agrega en una lista "botones" para acceder a ellos cuando querramos
    //Carga las piezas en el tablero
    private void cargar(){
        int contar=0;
        for (int i=0;i<35;i++){
            grupos.get(i).setEstado(0);
            ImagenBoton(i,grupos.get(i).getCantidad(),1);
            if(grupos.get(i).getId()==0){
                contar++;
            }
        }
        int total=35-contar;
        int soldado=0;
        int momia=0;
        for (int i=0;i<35;i++){
            if(grupos.get(i).getId()==1){
                soldado++;
            }else if(grupos.get(i).getId()==2){
                momia++;
            }
        }
        if(soldado==total){
            jugando=false;
            Ganador();
        }
    }
    private void ImagenBoton(int n1, int n2, int n3){
        if(n3==1){
            if(grupos.get(n1).getId()==1){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.m1_soldado6);
                        break;
                }
            }
            else if(grupos.get(n1).getId()==2){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.m1_momia1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.m1_momia2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.m1_momia3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.m1_momia4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.m1_momia5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.m1_momia6);
                        break;
                }
            }else{
                botones.get(n1).setImageResource(R.drawable.n);
            }
        }
        else{
            if(accion==1){
                if(grupos.get(n1).getId()==1){
                    switch (n2){
                        case 1:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado1);
                            break;
                        case 2:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado2);
                            break;
                        case 3:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado3);
                            break;
                        case 4:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado4);
                            break;
                        case 5:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado5);
                            break;
                        case 6:
                            botones.get(n1).setImageResource(R.drawable.m1_r_soldado6);
                            break;
                    }
                }
                else if(grupos.get(n1).getId()==2){
                    switch (n2){
                        case 1:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia1);
                            break;
                        case 2:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia2);
                            break;
                        case 3:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia3);
                            break;
                        case 4:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia4);
                            break;
                        case 5:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia5);
                            break;
                        case 6:
                            botones.get(n1).setImageResource(R.drawable.m1_r_momia6);
                            break;
                    }
                }else{
                    botones.get(n1).setImageResource(R.drawable.n);
                }
            }
        }
    }
    //Funcion que recibe un numero entre 0 y 100 (porcentaje de resultado que espero) y me devuelve true o false
    
    @Override
    public void onClick(View view) {
        accion=1;
        if(incrementando==false){
            switch (view.getId()){
                case R.id.btn1:
                    Jugar(0);
                    break;
                case R.id.btn2:
                    Jugar(1);
                    break;
                case R.id.btn3:
                    Jugar(2);
                    break;
                case R.id.btn4:
                    Jugar(3);
                    break;
                case R.id.btn5:
                    Jugar(4);
                    break;
                case R.id.btn6:
                    Jugar(5);
                    break;
                case R.id.btn7:
                    Jugar(6);
                    break;
                case R.id.btn8:
                    Jugar(7);
                    break;
                case R.id.btn9:
                    Jugar(8);
                    break;
                case R.id.btn10:
                    Jugar(9);
                    break;
                case R.id.btn11:
                    Jugar(10);
                    break;
                case R.id.btn12:
                    Jugar(11);
                    break;
                case R.id.btn13:
                    Jugar(12);
                    break;
                case R.id.btn14:
                    Jugar(13);
                    break;
                case R.id.btn15:
                    Jugar(14);
                    break;
                case R.id.btn16:
                    Jugar(15);
                    break;
                case R.id.btn17:
                    Jugar(16);
                    break;
                case R.id.btn18:
                    Jugar(17);
                    break;
                case R.id.btn19:
                    Jugar(18);
                    break;
                case R.id.btn20:
                    Jugar(19);
                    break;
                case R.id.btn21:
                    Jugar(20);
                    break;
                case R.id.btn22:
                    Jugar(21);
                    break;
                case R.id.btn23:
                    Jugar(22);
                    break;
                case R.id.btn24:
                    Jugar(23);
                    break;
                case R.id.btn25:
                    Jugar(24);
                    break;
                case R.id.btn26:
                    Jugar(25);
                    break;
                case R.id.btn27:
                    Jugar(26);
                    break;
                case R.id.btn28:
                    Jugar(27);
                    break;
                case R.id.btn29:
                    Jugar(28);
                    break;
                case R.id.btn30:
                    Jugar(29);
                    break;
                case R.id.btn31:
                    Jugar(30);
                    break;
                case R.id.btn32:
                    Jugar(31);
                    break;
                case R.id.btn33:
                    Jugar(32);
                    break;
                case R.id.btn34:
                    Jugar(33);
                    break;
                case R.id.btn35:
                    Jugar(34);
                    break;
            }
        }

    }
    //Boton TERMINAR TURNO
    public void Turno_onClick(View v){
        if(incrementando==false) {
            terminar();
        }
    }
    public void terminar(){
        if(N<10){
            N++;
        }
        incrementando=true;
        if(turno==1) {
            turno=2;
        }
        else{
            turno=1;
        }
        cargaLenta();
        contador = 0;
        NumeroEnJuego = 0;
        cargar();
    }
    //Pinta los espacios a los que me puedo mover
    public void Finish_onClick(View v){
        finish();
    }
    private void marcar(int id, int x, int y){
        //Primer condicion que no esté en una esquina
        //Segunda condicion que no esté al lado de un campo de juego nulo
        //Tercera condicion que el campo seleccionado no sea nulo
        //Cuarta condicion que el campo no tenga un valor de 1
        //Quinta condicion que el campo de al lado no sea de su mismo equipo
        if(x>0 && getGrupo(x-1,y).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x-1,y).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x-1,y).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x-1,y).getNumero(),getGrupo(x-1,y).getCantidad(),2);
        }
        if(x<6 && getGrupo(x+1,y).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x+1,y).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x+1,y).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x+1,y).getNumero(),getGrupo(x+1,y).getCantidad(),2);
        }
        if(y>0 && getGrupo(x,y-1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y-1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y-1).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x,y-1).getNumero(),getGrupo(x,y-1).getCantidad(),2);
        }
        if(y<4 && getGrupo(x,y+1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y+1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y+1).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x,y+1).getNumero(),getGrupo(x,y+1).getCantidad(),2);
        }
        grupos.get(getGrupo(x,y).getNumero()).setEstado(2);
    }
    //Recibe la posicion y devuelve el grupo
    private Grupo getGrupo(int x, int y){
        Boolean validar = false;
        Grupo g = new Grupo();
        for(int i=0;i<35;i++){
            if(grupos.get(i).getPosX() == x && grupos.get(i).getPosY() == y){
                g=grupos.get(i);
            }
        }
        return g;
    }
    //Metodo para jugar
    private void Jugar(int n){
        if(grupos.get(n).getEstado()==0){
            cargar();
            NumeroEnJuego = n;
            marcar(grupos.get(n).getId(),grupos.get(n).getPosX(),grupos.get(n).getPosY());
            grupos.get(n).setEstado(0);
        }else if(grupos.get(n).getEstado()==1){
            cambio(n,NumeroEnJuego);
            grupos.get(n).setEstado(0);
            cargar();
        }

    }
    //Recive el numero de la posicion atacada y la que esta atacando, aplica formula, marca un ganador y su cantidad
    private void cambio(int n1, int n2){
        int Ganador;
        Grupo Atacado = grupos.get(n1);
        Grupo Atacante = grupos.get(n2);

        if(Atacante.getCantidad() > Atacado.getCantidad()){
            //Gana Atacante
            Ganador = Atacante.getCantidad() - Atacado.getCantidad();
            grupos.get(n1).setId(grupos.get(n2).getId());
            grupos.get(n1).setCantidad(Ganador);
        }else if(Atacado.getCantidad() > Atacante.getCantidad()){
            //Gana Atacado
            Ganador = Atacado.getCantidad() - Atacante.getCantidad();
            grupos.get(n1).setCantidad(Ganador);
        }
        else {
            if(Herramientas.porcentaje(50)){
                //Gana Atacante
                grupos.get(n1).setId(grupos.get(n2).getId());
            }
            Ganador = 1;
            grupos.get(n1).setCantidad(Ganador);
        }
        grupos.get(n2).setCantidad(1);
    }

    private List<Integer> celdasAfectadas(){
        //Saco la lista de los numeros de las celdas a alterar y la almaceno en el arreglo "celdas"
        List<Integer> celdas = new ArrayList<>();
        if(turno==2){
            for(int i=0 ;i<grupos.size();i++){
                if(grupos.get(i).getId()==1 && grupos.get(i).getCantidad()<6){
                    celdas.add(grupos.get(i).getNumero());
                }
            }
        }
        else{
            for(int i=0 ;i<grupos.size();i++){
                if(grupos.get(i).getId()==2 && grupos.get(i).getCantidad()<6){
                    celdas.add(grupos.get(i).getNumero());
                }
            }
        }
        return celdas;
    }
    //
    //Incrementa las tropas en cada celda
    public void cargaLenta() {
        if (jugando == true){
            accion = 0;
        int repeticiones = (celdasAfectadas().size() + 1) * 100;
        final List<Integer> celdas = new ArrayList<>();
        new CountDownTimer(repeticiones, 100) {
            int num = 0;

            public void onTick(long millisUntilFinished) {
                int numeroDeIncremento = 0;
                try {
                    if (turno == 2 && num == 0) {
                        for (int i = 0; i < grupos.size(); i++) {
                            if (grupos.get(i).getId() == 1 && grupos.get(i).getCantidad() < 6) {
                                celdas.add(grupos.get(i).getNumero());
                            }
                            if (grupos.get(i).getId() == 1) {
                                numeroDeIncremento++;
                            }
                        }
                    } else if (turno == 1 && num == 0) {
                        for (int i = 0; i < grupos.size(); i++) {
                            if (grupos.get(i).getId() == 2 && grupos.get(i).getCantidad() < 6) {
                                celdas.add(grupos.get(i).getNumero());
                            }
                            if (grupos.get(i).getId() == 2) {
                                numeroDeIncremento++;
                            }
                        }
                    }
                    int incre=0;
                    for(int i=0; i<grupos.size();i++){
                        if(turno==2 && grupos.get(i).getId()==1){
                            incre++;
                        }else if(turno==1 && grupos.get(i).getId()==2){
                            incre++;
                        }
                    }
                    int vacios = 0;
                    for (int i = 0; i < grupos.size(); i++) {
                        if (grupos.get(i).getId() == 0) {
                            vacios++;
                        }
                    }
                    int cuartaParte = (35 - vacios) / 4;
                    //Si es el primer incremento
                    if (N >= 1 && N <= 2) {
                        if (Herramientas.porcentaje(90)) {
                            grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                        }
                    }
                    //Si es el segundo incremento
                    else if (N >= 3 && N <= 4) {
                        if (grupos.get(celdas.get(num)).getCantidad() <= 4) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(30)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                            //Si va nivelado
                            else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(20)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                            //Si va ganando
                            else if (incre > (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                        } else {
                            grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                        }
                    }
                    //Si es el tercer incremento
                    else if (N >= 5 && N <= 6) {
                        if (grupos.get(celdas.get(num)).getCantidad() <= 3) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(30)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else if (Herramientas.porcentaje(60)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                            //Si va nivelado
                            else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(20)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                            //Si va ganando
                            else if (incre > (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                        } else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(30)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }

                        } else {
                            grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                        }
                    }
                    //Si es el cuarto intento
                    else if (N >= 7 && N <= 8) {
                        if (grupos.get(celdas.get(num)).getCantidad() == 1) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else if (Herramientas.porcentaje(60)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else if (Herramientas.porcentaje(70)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 5);
                                }
                            }
                            //Si va nivelado
                            else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(70)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else if (Herramientas.porcentaje(70)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 5);
                                }
                            }
                            //Si va ganando
                            else if (incre > (cuartaParte * 3)) {
                                if (Herramientas.porcentaje(70)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 5);
                                }
                            }
                        } else if (grupos.get(celdas.get(num)).getCantidad() == 2) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else if (Herramientas.porcentaje(60)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(70)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                }
                            }
                        } else if (grupos.get(celdas.get(num)).getCantidad() == 3) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(20)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                        } else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(20)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                        } else {
                            grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                        }
                    }
                    //Si es el quinto intento
                    else {
                        if (grupos.get(celdas.get(num)).getCantidad() == 1) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(30)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 5);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 5);
                                }
                            }
                        } else if (grupos.get(celdas.get(num)).getCantidad() == 2) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(40)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 4);
                                }
                            }
                        }
                        else if (grupos.get(celdas.get(num)).getCantidad() == 3) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 3);
                                }
                            }
                        }
                        else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                            //Si va perdiendo
                            if (incre < cuartaParte) {
                                if (Herramientas.porcentaje(50)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                            //Si va nivelado o ganando
                            else {
                                if (Herramientas.porcentaje(10)) {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                                } else {
                                    grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 2);
                                }
                            }
                        } else {
                            grupos.get(celdas.get(num)).setCantidad(grupos.get(celdas.get(num)).getCantidad() + 1);
                        }
                    }
                }catch (Exception e){

                }
                num++;
                cargar();
            }
            public void onFinish() {
                cargar();
                contador = 0;
                NumeroEnJuego = 0;
                incrementando = false;
                if (turno == 2) {
                    incrementando = true;
                    new Thread(new Ultron()).start();
                }
            }

        }.start();
    }
    }

    public boolean Seleccionador(){
        boolean aceptar = true;
        List<Integer> gruposEnJuego = new ArrayList<>();
        for(int i=0;i<35;i++){
            if(grupos.get(i).getId()==2 && grupos.get(i).getCantidad()>1){
                gruposEnJuego.add(grupos.get(i).getNumero());
            }
        }
        boolean seguir2=true;
        int i=0;
        int b=0;
        if(N==3){
            b++;
        }
        b++;
        while (seguir2 && i<gruposEnJuego.size()){
            //Si no esta en la primera fila y la celda que esta arriba es del equipo contrario y el numero es mayor al numero que esta abajo
            if(grupos.get(gruposEnJuego.get(i)).getPosX()>0 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()-1,grupos.get(gruposEnJuego.get(i)).getPosY()).getId()==1 &&
                getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() > getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()-1,grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad()) {
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX() - 1, grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    seguir2 = false;
            }
            //Si no esta en la ultima fila y la celda que esta abajo es del equipo contrario y el numero es mayor al numero que esta arriba
            else if(grupos.get(gruposEnJuego.get(i)).getPosX()<6 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()+1,grupos.get(gruposEnJuego.get(i)).getPosY()).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() > getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()+1,grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad()) {
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX() + 1, grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    seguir2 = false;
            }
            //Si no esta en la primera columna y la columna que esta a la izquierda es del equipo contrario y el numero es mayor al numero que esta a la izquierda
            else if(grupos.get(gruposEnJuego.get(i)).getPosY()>0 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()-1).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() > getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()-1).getCantidad()) {
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY() - 1).getNumero());
                    seguir2 = false;
            }
            //Si no esta en la ultima columna y la columna que esta a la derecha es del equipo contrario y el numero es mayor al numero que esta a la derecha
            else if(grupos.get(gruposEnJuego.get(i)).getPosY()<4 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()+1).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() > getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()+1).getCantidad()) {
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY() + 1).getNumero());
                    seguir2 = false;
            }

            //Si no esta en la primera fila y la celda que esta arriba es del equipo contrario y el numero es igual al numero que esta abajo
            else if(grupos.get(gruposEnJuego.get(i)).getPosX()>0 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()-1,grupos.get(gruposEnJuego.get(i)).getPosY()).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() == getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()-1,grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad()) {
                if(Herramientas.porcentaje(50)){
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX() - 1, grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    seguir2 = false;
                }
            }
            //Si no esta en la ultima fila y la celda que esta abajo es del equipo contrario y el numero es igual al numero que esta arriba
            else if(grupos.get(gruposEnJuego.get(i)).getPosX()<6 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()+1,grupos.get(gruposEnJuego.get(i)).getPosY()).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() == getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX()+1,grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad()) {
                if(Herramientas.porcentaje(50)){
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX() + 1, grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    seguir2 = false;
                }
            }
            //Si no esta en la primera columna y la columna que esta a la izquierda es del equipo contrario y el numero es igual al numero que esta a la izquierda
            else if(grupos.get(gruposEnJuego.get(i)).getPosY()>0 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()-1).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() == getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()-1).getCantidad()) {
                if(Herramientas.porcentaje(50)){
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY() - 1).getNumero());
                    seguir2 = false;
                }
            }
            //Si no esta en la ultima columna y la columna que esta a la derecha es del equipo contrario y el numero es igual al numero que esta a la derecha
            else if(grupos.get(gruposEnJuego.get(i)).getPosY()<4 && getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()+1).getId()==1 &&
                    getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()).getCantidad() == getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(),grupos.get(gruposEnJuego.get(i)).getPosY()+1).getCantidad()) {
                if(Herramientas.porcentaje(50)) {
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY()).getNumero());
                    Jugar(getGrupo(grupos.get(gruposEnJuego.get(i)).getPosX(), grupos.get(gruposEnJuego.get(i)).getPosY() + 1).getNumero());
                    seguir2 = false;
                }
            }

            i++;

        }
        if(i==gruposEnJuego.size() && seguir2){
            aceptar=false;
        }
        return aceptar;
    }
    final class Ultron implements Runnable{
        boolean seguir=true;
        int j=0;
        //run ejecuta el proceso secundario que queremos hacer
        @Override
        public void run() {
            while(seguir){
                //Metodo de espera
                metodoEspera();
                Correr.post(new Runnable() {
                    @Override
                    public void run() {
                        if(seguir==true){
                            if(jugando==false){
                                seguir=false;
                            }
                            if(!Seleccionador()){
                                seguir=false;
                                j++;
                                if(j==1){
                                    cargar();
                                    contador = 0;
                                    NumeroEnJuego = 0;
                                    terminar();
                                }
                            }
                        }

                    }
                });

            }
        }
        private void metodoEspera() {
            try{
                Thread.sleep(2000);
            }catch (Exception e){}
        }

    }
    public void Ganador(){
        int niveles_superados;
        if(this.Desbloqueados!=null){
            niveles_superados = Integer.parseInt(Desbloqueados.getString(Mundo1.KEY,""))+1;
            if(Nivel == niveles_superados){
                SharedPreferences.Editor editor = this.Desbloqueados.edit();
                editor.putString(Mundo1.KEY,Integer.toString(niveles_superados));
                if(editor.commit()){
                    Log.d("TAG","Informacion guardada");
                }else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }
        Intent intent = new Intent(this, Ganador.class);
        intent.putExtra("nivel",Integer.toString(Nivel));
        startActivity(intent);
    }
    @Override
    protected void onPause(){
        super.onPause();
        fin = true;
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(fin)
        finish();
    }
}