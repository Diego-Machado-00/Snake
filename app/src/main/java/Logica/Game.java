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

    public void iniciarJuego(){
        this.snake = new Snake();
        this.comida = new Comida(this.snake);
        this.puntaje = 0;
        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(this.snake.getCuerpo());
        elemento.add(this.comida.getComida());
        this.setChanged();
        this.notifyObservers(elemento);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(Constantes.MOVIMIENTO!=""){
                    moverSnake(Constantes.MOVIMIENTO);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,0,1000);
    }

    public void moverSnake(String movimiento){
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
        if(!esLimite){
            this.snake.mover(this.direccionX,this.direccionY);
            this.colisionComida();
            boolean isMuerte = this.muerteSnake();
            if(!isMuerte){
                ArrayList<Object> elemento = new ArrayList<Object>();
                elemento.add(this.snake.getCuerpo());
                elemento.add(this.comida.getComida());
                elemento.add(puntoF);
                this.setChanged();
                this.notifyObservers(elemento);
            }else{
                this.gameOver();
            }
        }else{
            this.gameOver();
        }

    }

    private void colisionComida() {
        if(this.snake.obtenerCabeza().equals(this.comida.getComida())){
            this.snake.crecerSnake();
            this.comida.nuevaComida(this.snake);
            this.puntaje += 1;
        }
    }

    private boolean muerteSnake(){
        for(int i = 1 ; i < this.snake.largoSnake() ; i++){
            if(this.snake.getCuerpo().get(0).equals(this.snake.getCuerpo().get(i))){
                return true;
            }
        }
        return false;
    }

    private void gameOver(){

    }

}
