package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import Controlador.ControlTouch;
import Logica.Snake;

public class MainActivity extends AppCompatActivity {

    private DibujoMatriz dibujoMatriz;
    private ControlTouch controlTouch;
    private Puntuacion puntuacion;
    private Snake snake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dibujoMatriz = new DibujoMatriz(this);
        this.puntuacion = new Puntuacion(this);
        this.controlTouch = new ControlTouch(this);
        this.snake = new Snake();
        this.snake.addObserver(this.dibujoMatriz);
        this.snake.iniciarJuego();

    }

    public void moverSnake(String movimiento) {
        this.snake.mover(movimiento);
    }
}