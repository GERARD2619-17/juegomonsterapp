package com.example.juego;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.juego.clases.Grupo;
import com.example.juego.clases.Herramientas;
import com.example.juego.clases.Jugador;
import com.example.juego.clases.NivelesMundo3;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Multijugador extends AppCompatActivity implements View.OnClickListener{

    private String keis, rival, miturno, yo, KeyId, KeyJugada;
    private int accion, NumeroEnJuego, turno, N, contadorLista;
    public boolean jugando, enTurno, incrementando;
    private static int contador=0;

    private List<Grupo> grupos = new ArrayList<>();
    private List<ImageButton> botones = new ArrayList<>();
    private List<String> listadoStrings = new ArrayList<>();

    private FirebaseDatabase database;
    private DatabaseReference databasePartida;
    private DatabaseReference jugada;
    private DatabaseReference jugadasdelosjugadores;

    private TextView txtTituloNivel,txtTurnoNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multijugador);
        this.keis = getIntent().getStringExtra("key");
        this.rival = getIntent().getStringExtra("rival");
        this.miturno = getIntent().getStringExtra("turno");
        this.yo = getIntent().getStringExtra("yo");
        this.KeyId = "";
        this.KeyJugada = "";

        this.accion=0;
        this.NumeroEnJuego = 0;
        this.turno=1;
        this.N=0;
        this.contadorLista=0;
        this.incrementando=false;

        jugando=true;

        this.txtTituloNivel = findViewById(R.id.txtTituloNivel);
        this.txtTurnoNivel = findViewById(R.id.txtTurnoNivel);

        txtTituloNivel.setText(yo + " vs " + rival);

        this.database = FirebaseDatabase.getInstance();
        this.databasePartida = database.getReference(keis);
        this.jugada = database.getReference(keis+"jugadas");
        this.jugadasdelosjugadores = database.getReference(keis+"movimientos");

        if(Integer.parseInt(miturno)==1){
            this.enTurno = true;
            txtTurnoNivel.setText("turno: "+yo);
        }else {
            this.enTurno = false;
            txtTurnoNivel.setText("turno: "+rival);
        }
        agregarBotones();
        cargarGrupos();
        cargar();

        databasePartida.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Grupo g = snapshot.getValue(Grupo.class);
                Jugar(g.getNumero());
                databasePartida.child(KeyId).removeValue();
                KeyId="";
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
        jugada.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Jugador j = snapshot.getValue(Jugador.class);
                if(j.getEstado().equals("rendicion")){
                    Cierre(j);
                }else {
                int n1 = Integer.parseInt(j.getNombre());
                int aidi = Integer.parseInt(j.getEstado());
                grupos.get(n1).setId(aidi);
                jugada.child(j.getId()).removeValue();
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
        jugadasdelosjugadores.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Grupo g = snapshot.getValue(Grupo.class);
                grupos.get(g.getNumero()).setCantidad(g.getCantidad());
                cargar();
                turno = g.getId();
                if((Integer.parseInt(miturno)==1)){
                    if(turno==1){
                        txtTurnoNivel.setText("turno: "+yo);
                    }else {
                        txtTurnoNivel.setText("turno: "+rival);
                    }
                }else {
                    if(turno==1){
                        txtTurnoNivel.setText("turno: "+rival);
                    }else {
                        txtTurnoNivel.setText("turno: "+yo);
                    }
                }

                listadoStrings.add(KeyJugada);
                //jugadasdelosjugadores.child(KeyJugada).removeValue();
                KeyJugada="";
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
    }
    //Genera las piezas en el tablero y los agrega en la lista "grupos" para acceder a ellos cuando querramos
    private void cargarGrupos(){
        NivelesMundo3 nivel = new NivelesMundo3(2);
        grupos = nivel.getNivelesMundo3();
    }

    //Carga e inicaliza los botones, en un metodo y luego los agrega en una lista "botones" para acceder a ellos cuando querramos
    //Carga las piezas en el tablero
    private void cargar(){
        int contar=0;
        for (int i=0;i<30;i++){
            grupos.get(i).setEstado(0);
            ImagenBoton(i,grupos.get(i).getCantidad(),1);
            if(grupos.get(i).getId()==0){
                contar++;
            }
        }
        int total=30-contar;
        Evaluar(total);
    }

    private void ImagenBoton(int n1, int n2, int n3){
        if(n3==1){
            if(grupos.get(n1).getId()==1){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.m3_soldado6);
                        break;
                }
            }
            else if(grupos.get(n1).getId()==2){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.m3_fuego6);
                        break;
                }
            }else{
                botones.get(n1).setImageResource(R.drawable.base2);
            }
        }
        else{
                if(grupos.get(n1).getId()==1){
                    switch (n2){
                        case 1:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado1);
                            break;
                        case 2:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado2);
                            break;
                        case 3:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado3);
                            break;
                        case 4:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado4);
                            break;
                        case 5:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado5);
                            break;
                        case 6:
                            botones.get(n1).setImageResource(R.drawable.m3_a_soldado6);
                            break;
                    }
                }
                else if(grupos.get(n1).getId()==2){
                    switch (n2){
                        case 1:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego1);
                            break;
                        case 2:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego2);
                            break;
                        case 3:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego3);
                            break;
                        case 4:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego4);
                            break;
                        case 5:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego5);
                            break;
                        case 6:
                            botones.get(n1).setImageResource(R.drawable.m3_a_fuego6);
                            break;
                    }
                }else{
                    botones.get(n1).setImageResource(R.drawable.n);
                }

        }
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
    //Recibe la posicion y devuelve el grupo
    private Grupo getGrupo(int x, int y){
        Grupo g = new Grupo();
        for(int i=0;i<30;i++){
            if(grupos.get(i).getPosX() == x && grupos.get(i).getPosY() == y){
                g=grupos.get(i);
            }
        }
        return g;
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
        if(x<5 && getGrupo(x+1,y).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x+1,y).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x+1,y).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x+1,y).getNumero(),getGrupo(x+1,y).getCantidad(),2);
        }
        if(y>0 && getGrupo(x,y-1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y-1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y-1).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x,y-1).getNumero(),getGrupo(x,y-1).getCantidad(),2);
        }
        if(y<4 && getGrupo(x,y+1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y+1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y+1).getNumero()).setEstado(1);
            int n1 = getGrupo(x,y+1).getNumero();
            int n2 = getGrupo(x,y+1).getCantidad();
            ImagenBoton(n1,n2,2);
        }
        grupos.get(getGrupo(x,y).getNumero()).setEstado(2);
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
                String key = jugada.push().getKey();
                Jugador j = new Jugador(key,Integer.toString(n1),Integer.toString(grupos.get(n2).getId()),"");
                jugada.child(key).setValue(j);
                //grupos.get(n1).setId(grupos.get(n2).getId());
            }
            Ganador = 1;
            grupos.get(n1).setCantidad(Ganador);
        }
        grupos.get(n2).setCantidad(1);
    }

    @Override
    public void onClick(View view) {
        accion=1;
        if(Integer.parseInt(miturno)==turno){
            String key = databasePartida.push().getKey();
            KeyId = key;
            switch (view.getId()){
                case R.id.btn1:
                    databasePartida.child(key).setValue(grupos.get(0));
                    break;
                case R.id.btn2:
                    databasePartida.child(key).setValue(grupos.get(1));
                    break;
                case R.id.btn3:
                    databasePartida.child(key).setValue(grupos.get(2));
                    break;
                case R.id.btn4:
                    databasePartida.child(key).setValue(grupos.get(3));
                    break;
                case R.id.btn5:
                    databasePartida.child(key).setValue(grupos.get(4));
                    break;
                case R.id.btn6:
                    databasePartida.child(key).setValue(grupos.get(5));
                    break;
                case R.id.btn7:
                    databasePartida.child(key).setValue(grupos.get(6));
                    break;
                case R.id.btn8:
                    databasePartida.child(key).setValue(grupos.get(7));
                    break;
                case R.id.btn9:
                    databasePartida.child(key).setValue(grupos.get(8));
                    break;
                case R.id.btn10:
                    databasePartida.child(key).setValue(grupos.get(9));
                    break;
                case R.id.btn11:
                    databasePartida.child(key).setValue(grupos.get(10));
                    break;
                case R.id.btn12:
                    databasePartida.child(key).setValue(grupos.get(11));
                    break;
                case R.id.btn13:
                    databasePartida.child(key).setValue(grupos.get(12));
                    break;
                case R.id.btn14:
                    databasePartida.child(key).setValue(grupos.get(13));
                    break;
                case R.id.btn15:
                    databasePartida.child(key).setValue(grupos.get(14));
                    break;
                case R.id.btn16:
                    databasePartida.child(key).setValue(grupos.get(15));
                    break;
                case R.id.btn17:
                    databasePartida.child(key).setValue(grupos.get(16));
                    break;
                case R.id.btn18:
                    databasePartida.child(key).setValue(grupos.get(17));
                    break;
                case R.id.btn19:
                    databasePartida.child(key).setValue(grupos.get(18));
                    break;
                case R.id.btn20:
                    databasePartida.child(key).setValue(grupos.get(19));
                    break;
                case R.id.btn21:
                    databasePartida.child(key).setValue(grupos.get(20));
                    break;
                case R.id.btn22:
                    databasePartida.child(key).setValue(grupos.get(21));
                    break;
                case R.id.btn23:
                    databasePartida.child(key).setValue(grupos.get(22));
                    break;
                case R.id.btn24:
                    databasePartida.child(key).setValue(grupos.get(23));
                    break;
                case R.id.btn25:
                    databasePartida.child(key).setValue(grupos.get(24));
                    break;
                case R.id.btn26:
                    databasePartida.child(key).setValue(grupos.get(25));
                    break;
                case R.id.btn27:
                    databasePartida.child(key).setValue(grupos.get(26));
                    break;
                case R.id.btn28:
                    databasePartida.child(key).setValue(grupos.get(27));
                    break;
                case R.id.btn29:
                    databasePartida.child(key).setValue(grupos.get(28));
                    break;
                case R.id.btn30:
                    databasePartida.child(key).setValue(grupos.get(29));
                    break;
            }
        }

    }

    //Boton TERMINAR TURNO
    public void Turno_onClick(View v){
        if(Integer.parseInt(miturno)==turno){
            if(!incrementando) {
                LimpiarLista();
                terminar();
            }
        }

    }
    public void terminar(){
        if(N<5){
            N++;
        }
        incrementando=true;
        cargaLenta();
        contador = 0;
        NumeroEnJuego = 0;
        cargar();
    }
    private List<Integer> celdasAfectadas(){
        //Saco la lista de los numeros de las celdas a alterar y la almaceno en el arreglo "celdas"
        List<Integer> celdas = new ArrayList<>();
        if(turno==1){
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
                                if (grupos.get(i).getId() == 2 && grupos.get(i).getCantidad() < 6) {
                                    celdas.add(grupos.get(i).getNumero());
                                }
                                if (grupos.get(i).getId() == 2) {
                                    numeroDeIncremento++;
                                }
                            }
                        } else if (turno == 1 && num == 0) {
                            for (int i = 0; i < grupos.size(); i++) {
                                if (grupos.get(i).getId() == 1 && grupos.get(i).getCantidad() < 6) {
                                    celdas.add(grupos.get(i).getNumero());
                                }
                                if (grupos.get(i).getId() == 1) {
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
                        int cuartaParte = (30 - vacios) / 4;
                        //Si es el primer incremento
                        if (N == 1) {
                            if (Herramientas.porcentaje(90)) {
                                String key = jugadasdelosjugadores.push().getKey();
                                int I;
                                if(grupos.get(celdas.get(num)).getId()==1){
                                    I=2;
                                }else {
                                    I=1;
                                }
                                KeyJugada = key;
                                Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                jugadasdelosjugadores.child(key).setValue(g);
                            }
                        }
                        //Si es el segundo incremento
                        else if (N == 2) {
                            if (grupos.get(celdas.get(num)).getCantidad() <= 4) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(30)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado
                                else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(20)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va ganando
                                else if (incre > (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else {
                                String key = jugadasdelosjugadores.push().getKey();
                                KeyJugada = key;
                                int I;
                                if(grupos.get(celdas.get(num)).getId()==1){
                                    I=2;
                                }else {
                                    I=1;
                                }
                                Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                jugadasdelosjugadores.child(key).setValue(g);
                            }
                        }
                        //Si es el tercer incremento
                        else if (N == 3) {
                            if (grupos.get(celdas.get(num)).getCantidad() <= 3) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(30)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(60)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado
                                else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(20)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va ganando
                                else if (incre > (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(30)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }

                            } else {
                                String key = jugadasdelosjugadores.push().getKey();
                                KeyJugada = key;
                                int I;
                                if(grupos.get(celdas.get(num)).getId()==1){
                                    I=2;
                                }else {
                                    I=1;
                                }
                                Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                jugadasdelosjugadores.child(key).setValue(g);
                            }
                        }
                        //Si es el cuarto intento
                        else if (N == 4) {
                            if (grupos.get(celdas.get(num)).getCantidad() == 1) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(60)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(70)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 5),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado
                                else if (incre > cuartaParte && incre < (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(70)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(70)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 5),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va ganando
                                else if (incre > (cuartaParte * 3)) {
                                    if (Herramientas.porcentaje(70)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 5),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else if (grupos.get(celdas.get(num)).getCantidad() == 2) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(60)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(70)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else if (grupos.get(celdas.get(num)).getCantidad() == 3) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(20)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(20)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else {
                                String key = jugadasdelosjugadores.push().getKey();
                                KeyJugada = key;
                                int I;
                                if(grupos.get(celdas.get(num)).getId()==1){
                                    I=2;
                                }else {
                                    I=1;
                                }
                                Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                jugadasdelosjugadores.child(key).setValue(g);
                            }
                        }
                        //Si es el quinto intento
                        else {
                            if (grupos.get(celdas.get(num)).getCantidad() == 1) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(30)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 5),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 5),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else if (grupos.get(celdas.get(num)).getCantidad() == 2) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(40)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 4),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            }
                            else if (grupos.get(celdas.get(num)).getCantidad() == 3) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 3),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            }
                            else if (grupos.get(celdas.get(num)).getCantidad() == 4) {
                                //Si va perdiendo
                                if (incre < cuartaParte) {
                                    if (Herramientas.porcentaje(50)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                                //Si va nivelado o ganando
                                else {
                                    if (Herramientas.porcentaje(10)) {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    } else {
                                        String key = jugadasdelosjugadores.push().getKey();
                                        KeyJugada = key;
                                        int I;
                                        if(grupos.get(celdas.get(num)).getId()==1){
                                            I=2;
                                        }else {
                                            I=1;
                                        }
                                        Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 2),celdas.get(num));
                                        jugadasdelosjugadores.child(key).setValue(g);
                                    }
                                }
                            } else {
                                String key = jugadasdelosjugadores.push().getKey();
                                KeyJugada = key;
                                int I;
                                if(grupos.get(celdas.get(num)).getId()==1){
                                    I=2;
                                }else {
                                    I=1;
                                }
                                Grupo g = new Grupo(I,grupos.get(celdas.get(num)).getPosX(),grupos.get(celdas.get(num)).getPosY(),(grupos.get(celdas.get(num)).getCantidad() + 1),celdas.get(num));
                                jugadasdelosjugadores.child(key).setValue(g);
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
                }

            }.start();
        }
    }

    private void Evaluar(int total){
        int soldado=0;
        int fuego=0;
        for (int i=0;i<30;i++){
            if(grupos.get(i).getId()==1){
                soldado++;
            }else if(grupos.get(i).getId()==2){
                fuego++;
            }
        }
        if(soldado==total){
            LimpiarLista();
            Intent resultado = new Intent(this, Resultado.class);
            if(Integer.parseInt(miturno)==1){
                resultado.putExtra("resultado","1");
            }else {
                resultado.putExtra("resultado","2");
            }
            startActivity(resultado);
            finish();
            jugando=false;
        }else if(fuego==total){
            LimpiarLista();
            Intent resultado = new Intent(this, Resultado.class);
            if(Integer.parseInt(miturno)==2){
                resultado.putExtra("resultado","1");
            }else {
                resultado.putExtra("resultado","2");
            }
            startActivity(resultado);
            finish();
            jugando=false;
        }
    }

    private void LimpiarLista(){
        for(int i=0; i<listadoStrings.size();i++){
            jugadasdelosjugadores.child(listadoStrings.get(i)).removeValue();
        }
        for(int i=0; i<listadoStrings.size();i++){
            listadoStrings.remove(i);
        }
    }
    public void Salir_onClick(View v){
        LimpiarLista();
        String key = jugada.push().getKey();
        Jugador j = new Jugador(key,miturno,"rendicion","");//yo
        jugada.child(key).setValue(j);
        finish();
    }
    private void Cierre(Jugador j){
        LimpiarLista();
        Intent resultado = new Intent(this, Resultado.class);
        if(Integer.parseInt(miturno)==Integer.parseInt(j.getNombre())){
            jugada.child(j.getId()).removeValue();
            finish();
        }else {
            jugada.child(j.getId()).removeValue();
            resultado.putExtra("resultado","3");
            startActivity(resultado);
            finish();
        }
    }
}