package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import Controlador.ControlTouch;
import Logica.Game;
import Logica.Snake;
import Util.Constantes;

public class MainActivity extends AppCompatActivity {

    private DibujoMatriz dibujoMatriz;
    private ControlTouch controlTouch;
    private Puntuacion puntuacion;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dibujoMatriz = new DibujoMatriz(this);
        this.puntuacion = new Puntuacion(this);
        this.controlTouch = new ControlTouch(this);
        this.game = new Game();
        this.game.addObserver(this.dibujoMatriz);
        this.game.iniciarJuego();

    }

    public void moverSnake(String movimiento) {
        Constantes.MOVIMIENTO = movimiento;
        this.game.moverSnake(movimiento);
    }
}