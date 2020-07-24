package Logica;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Util.Constantes;

public class Snake {
    private static ArrayList<Point> cuerpo;

    public ArrayList<Point> getCuerpo() {
        return this.cuerpo;
    }

    public int largoSnake(){
        return this.cuerpo.size()-1;
    }

    public Point obtenerCabeza(){
        return this.cuerpo.get(0);
    }

    public  Point obtenerCola(){
        return this.cuerpo.get(this.cuerpo.size()-1);
    }

    public Snake(){
        this.cuerpo = new ArrayList<Point>();
        cuerpo.add(new Point(7,6));
        cuerpo.add(new Point(7,7));
        cuerpo.add(new Point(7,8));
    }

    public void crecerSnake(){
        int Xaux = this.cuerpo.get(this.cuerpo.size()-1).x;
        int Yaux = this.cuerpo.get(this.cuerpo.size()-1).y;
        this.cuerpo.add(new Point(Xaux,Yaux));
    }

    public void mover(int direccionX, int direccionY){
        for(int i = this.cuerpo.size()-1 ; i > 0 ; i--){
            this.cuerpo.get(i).x = this.cuerpo.get(i-1).x;
            this.cuerpo.get(i).y = this.cuerpo.get(i-1).y;
        }
        this.cuerpo.get(0).x += direccionX;
        this.cuerpo.get(0).y += direccionY;
    }
}
