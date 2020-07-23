package Logica;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Util.Constantes;

public class Snake extends Observable {
    private ArrayList<Point> cuerpo;
    private int direccionX;
    private int direccionY;

    public void iniciarJuego(){
        this.cuerpo = new ArrayList<Point>();
        this.AgregarSnake();
        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(this.cuerpo);
        this.setChanged();
        this.notifyObservers(elemento);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mover(Constantes.MOVIMIENTO);
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,0,1000);
    }



    public void AgregarSnake(){
        cuerpo.add(new Point(7,6));
        cuerpo.add(new Point(7,7));
        cuerpo.add(new Point(7,8));

    }

    public void mover(String movimiento){
        int puntoF [] = new int[2];
        puntoF[0] = this.cuerpo.get(this.cuerpo.size()-1).x;
        puntoF[1] = this.cuerpo.get(this.cuerpo.size()-1).y;
        boolean esLimite= false;
        if(movimiento == Constantes.IZQUIERDA){
            if(this.cuerpo.get(0).y != 0){
                this.direccionX = 0;
                this.direccionY = -1;
            }else{
                esLimite = true;
            }
        }else if(movimiento == Constantes.DERECHA){
            if(this.cuerpo.get(0).y != 9){
                this.direccionX = 0;
                this.direccionY = 1;
            }else{
                esLimite = true;
            }
        }else if(movimiento == Constantes.ARRIBA){
            if(this.cuerpo.get(0).x != 0){
                this.direccionX = -1;
                this.direccionY = 0;
            }else{
                esLimite = true;
            }
        }else if(movimiento == Constantes.ABAJO){
            if(this.cuerpo.get(0).x != 19){
                this.direccionX = 1;
                this.direccionY = 0;
            }else{
                esLimite = true;
            }
        }

        if(!esLimite){
            for(int i = this.cuerpo.size()-1 ; i > 0 ; i--){
                this.cuerpo.get(i).x = this.cuerpo.get(i-1).x;
                this.cuerpo.get(i).y = this.cuerpo.get(i-1).y;
            }
            this.cuerpo.get(0).x += this.direccionX;
            this.cuerpo.get(0).y += this.direccionY;
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(this.cuerpo);
            elemento.add(puntoF);
            this.setChanged();
            this.notifyObservers(elemento);
        }else{

        }

            }
}
