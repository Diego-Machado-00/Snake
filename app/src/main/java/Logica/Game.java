package Logica;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import Util.Constantes;

public class Game  extends Observable {
    private final static int FILAS = 20;
    private final static int COLUMNAS = 10;
    private int puntaje;
    private Snake snake;
    private Comida comida;
    private int direccionX;
    private int direccionY;
    private Tiempo tiempoJuego;
    private int tiempo;
    private Timer timer;
    private Timer timerT;


    public void iniciarJuego(){
        if(this.timer != null){
            this.timer.cancel();
        }
        this.snake = new Snake();
        this.comida = new Comida(this.snake);
        this.tiempoJuego = new Tiempo();
        this.puntaje = 0;
        this.tiempo = 1000;

        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(false);
        elemento.add(this.snake.getCuerpo());
        elemento.add(this.comida.getComida());
        this.setChanged();
        this.notifyObservers(elemento);

        final TimerTask timerJ = new TimerTask() {
            @Override
            public void run() {
                if(Constantes.MOVIMIENTO!=""){
                    tiempoJuego();
                }
            }
        };

        this.timerT =  new Timer();
        this.timerT.schedule(timerJ, 0, 1000);

        final TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                    if(Constantes.MOVIMIENTO!=""){
                       if(!moverSnake(Constantes.MOVIMIENTO)){
                           this.cancel();
                           timerT.cancel();
                       }
                    }
            }
        };
        this.timer = new Timer();
        this.timer.schedule(timerTask,0,this.tiempo);
    }

    public boolean moverSnake(String movimiento){
        int [] puntoF = new int[2];
        puntoF[0] = this.snake.obtenerCola().x;
        puntoF[1] = this.snake.obtenerCola().y;
        boolean esLimite= false;
        if(movimiento == Constantes.IZQUIERDA){
                if(this.snake.obtenerCabeza().y != 0){
                    this.direccionX = 0;
                    this.direccionY = -1;
                }else{
                    esLimite = true;
                }
        }else if(movimiento == Constantes.DERECHA){
                if(this.snake.obtenerCabeza().y != 9){
                    this.direccionX = 0;
                    this.direccionY = 1;
                }else{
                    esLimite = true;
                }
        }else if(movimiento == Constantes.ARRIBA){
            if(this.snake.obtenerCabeza().x != 0){
                    this.direccionX = -1;
                    this.direccionY = 0;
                }else{
                    esLimite = true;
                }

        }else if(movimiento == Constantes.ABAJO){
            if(this.snake.obtenerCabeza().x != 19){
                this.direccionX = 1;
                this.direccionY = 0;
            }else{
                esLimite = true;
            }
        }
        ArrayList<Object> elemento = new ArrayList<Object>();
        if(!esLimite){
            this.snake.mover(this.direccionX,this.direccionY);
            this.colisionComida();
            if(this.muerteSnake()){
                this.timer.cancel();
                this.timerT.cancel();
                elemento.add(true);
                elemento.add(this.puntaje);
                this.setChanged();
                this.notifyObservers(elemento);
                return false;
            }else{
                elemento.add(false);
                elemento.add(this.snake.getCuerpo());
                elemento.add(this.comida.getComida());
                elemento.add(puntoF);
                elemento.add(this.puntaje);
                this.setChanged();
                this.notifyObservers(elemento);
            }
        }else{
            this.timer.cancel();
            this.timerT.cancel();
            elemento.add(true);
            elemento.add(this.puntaje);
            this.setChanged();
            this.notifyObservers(elemento);
            return false;
        }
        return true;

    }
    
    private void colisionComida() {
        if(this.snake.obtenerCabeza().equals(this.comida.getComida())){
            this.snake.crecerSnake();
            this.comida.nuevaComida(this.snake);
            this.puntaje += 1;
            this.timer.cancel();
            this.timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if(Constantes.MOVIMIENTO!=""){
                        if(!moverSnake(Constantes.MOVIMIENTO)){
                            this.cancel();
                        }
                    }
                }
            };
            this.tiempo -= (this.puntaje > 15 ? 7 : 50);
            this.timer.schedule(timerTask,this.tiempo,this.tiempo);
        }

    }

    private boolean muerteSnake(){
        for(int i = 1 ; i < this.snake.largoSnake()+1 ; i++){
            if(this.snake.getCuerpo().get(0).equals(this.snake.getCuerpo().get(i))){
                return true;
            }
        }
        return false;
    }

    private void tiempoJuego(){
        this.tiempoJuego.tiempoJuego();
        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(null);
        elemento.add(this.tiempoJuego.getSeg());
        elemento.add(this.tiempoJuego.getMin());
        elemento.add(this.tiempoJuego.getHor());
        this.setChanged();
        this.notifyObservers(elemento);
    }

}
