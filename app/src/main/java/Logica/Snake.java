package Logica;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Observable;

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
    }

    public void AgregarSnake(){
        cuerpo.add(new Point(7,6));
        cuerpo.add(new Point(7,7));
        cuerpo.add(new Point(7,8));

    }

    public void mover(String movimiento){
        if(movimiento == Constantes.IZQUIERDA){
            this.direccionX = 0;
            this.direccionY = -1;
        }else if(movimiento == Constantes.DERECHA){
            this.direccionX = 0;
            this.direccionY = 1;
        }else if(movimiento == Constantes.ARRIBA){
            this.direccionX = -1;
            this.direccionY = 0;
        }else if(movimiento == Constantes.ABAJO){
            this.direccionX = 1;
            this.direccionY = 0;
        }
        for(int i = this.cuerpo.size() ; i > 0 ; i--){
            this.cuerpo.get(i).x = this.cuerpo.get(i-1).x;
            this.cuerpo.get(i).y = this.cuerpo.get(i-1).y;
        }
        this.cuerpo.get(0).x += this.direccionX;
        this.cuerpo.get(0).y += this.direccionY;
        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(this.cuerpo);
        this.setChanged();
        this.notifyObservers(elemento);
    }
}
