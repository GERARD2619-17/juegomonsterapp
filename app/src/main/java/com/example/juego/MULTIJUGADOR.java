package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juego.clases.Grupo;
import com.example.juego.clases.NivelesMundo1;

import java.util.ArrayList;
import java.util.List;
//Multijugador
public class MULTIJUGADOR extends AppCompatActivity implements View.OnClickListener{

    private List<Grupo> grupos = new ArrayList<>();
    private List<ImageButton> botones = new ArrayList<>();
    private int turno=1;
    private int NumeroEnJuego=0;
    private int turnos;
    private int Nivel;

    private static int contador=0;
    private Handler Correr;
    private boolean incrementando=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_u_l_t_i_j_u_g_a_d_o_r);
        this.Correr = new Handler();
        turnos = 0;
        if(getIntent().getStringExtra("nivel")!=null){
            Nivel = Integer.parseInt(getIntent().getStringExtra("nivel"));
        }
        else {
            Nivel=0;
        }
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
    //Genera los valores aleatorios de nuestras piezas en el tablero y los agrega en la lista "grupos" para acceder a ellos cuando querramos
    private void cargarGrupos(){
        if(Nivel!=0){
            NivelesMundo1 nivel = new NivelesMundo1(Nivel);
            grupos = nivel.getNivelesMundo1();
        }
        else {
            int contador = 0;
            for(int i = 1; i<=7; i++){
                for(int j = 1; j <=5; j++){
                    int id;
                    if(porcentaje(12)) id=0;
                    else id = (int)(Math.random() * 2) + 1;
                    int cantidad = (int)(Math.random() * 6) + 1;
                    Grupo g = new Grupo(id,i,j,cantidad,contador);
                    grupos.add(g);
                    contador++;
                }
            }
        }
    }
    //Carga las piezas en el tablero
    private void cargar(){
        for (int i=0;i<35;i++){
            grupos.get(i).setEstado(0);
            ImagenBoton(i,grupos.get(i).getCantidad(),1);
        }
    }
    private void ImagenBoton(int n1, int n2, int n3){
        if(n3==1){
            if(grupos.get(n1).getId()==1){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.fr1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.fr2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.fr3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.fr4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.fr5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.fr6);
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
            if(grupos.get(n1).getId()==1){
                switch (n2){
                    case 1:
                        botones.get(n1).setImageResource(R.drawable.rf1);
                        break;
                    case 2:
                        botones.get(n1).setImageResource(R.drawable.rf2);
                        break;
                    case 3:
                        botones.get(n1).setImageResource(R.drawable.rf3);
                        break;
                    case 4:
                        botones.get(n1).setImageResource(R.drawable.rf4);
                        break;
                    case 5:
                        botones.get(n1).setImageResource(R.drawable.rf5);
                        break;
                    case 6:
                        botones.get(n1).setImageResource(R.drawable.rf6);
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
    //Funcion que recibe un numero entre 0 y 100 (porcentaje de resultado que espero) y me devuelve true o false
    private boolean porcentaje(int n){
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
    @Override
    public void onClick(View view) {
        if(incrementando==false) {
            switch (view.getId()) {
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
        incrementando=true;
        //Incrementar();
        new Thread(new cargaLenta()).start();
        contador = 0;
        if(turno==1) {
            turno=2;
        }
        else{
            turno=1;
        }
        NumeroEnJuego = 0;
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
        if(x>1 && getGrupo(x-1,y).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x-1,y).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x-1,y).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x-1,y).getNumero(),getGrupo(x-1,y).getCantidad(),2);
            //botones.get(getGrupo(x-1,y).getNumero()).setBackgroundColor(Color.rgb(0,0,255));
        }
        if(x<7 && getGrupo(x+1,y).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x+1,y).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x+1,y).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x+1,y).getNumero(),getGrupo(x+1,y).getCantidad(),2);
            //botones.get(getGrupo(x+1,y).getNumero()).setBackgroundColor(Color.rgb(0,0,255));
        }
        if(y>1 && getGrupo(x,y-1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y-1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y-1).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x,y-1).getNumero(),getGrupo(x,y-1).getCantidad(),2);
            //botones.get(getGrupo(x,y-1).getNumero()).setBackgroundColor(Color.rgb(0,0,255));
        }
        if(y<5 && getGrupo(x,y+1).getId()!=0 && getGrupo(x,y).getId()!=0 && getGrupo(x,y).getCantidad()!=1 && getGrupo(x,y+1).getId()!=getGrupo(x,y).getId() && getGrupo(x,y).getId()==turno){
            grupos.get(getGrupo(x,y+1).getNumero()).setEstado(1);
            ImagenBoton(getGrupo(x,y+1).getNumero(),getGrupo(x,y+1).getCantidad(),2);
            //botones.get(getGrupo(x,y+1).getNumero()).setBackgroundColor(Color.rgb(0,0,255));
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
            if(porcentaje(50)){
            //Gana Atacante
            grupos.get(n1).setId(grupos.get(n2).getId());
            }
            Ganador = 1;
            grupos.get(n1).setCantidad(Ganador);
        }
        grupos.get(n2).setCantidad(1);
    }
    //Incrementa las tropas en cada celda
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
    //Incrementa las tropas en cada celda
    final class cargaLenta implements  Runnable{
        int n=0;
        @Override
        public void run() {
            while(contador< celdasAfectadas().size()-1){
                metodoEspera();
                Correr.post(new Runnable() {
                    @Override
                    public void run() {
                        contador=n;
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
                        //Si la celda es mayor a 6
                        if(grupos.get(celdas.get(n)).getCantidad()<6){
                            //tengo el 90% de incrementar su valor
                            if(porcentaje(90)){
                                grupos.get(celdas.get(n)).setCantidad(grupos.get(celdas.get(n)).getCantidad()+1);
                            }
                        }
                        n++;
                        cargar();
                        if(n==celdasAfectadas().size()){
                            incrementando=false;
                        }
                    }
                });
            }
        }
        private void metodoEspera() {
            try {
                Thread.sleep(500);
                contador++;
            }catch (Exception e ){

            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        //pausar();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action",AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }
}