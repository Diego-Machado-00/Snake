package com.example.snake;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Controlador.ControlTouch;
import Logica.Game;
import Util.Constantes;

public class MainActivity extends AppCompatActivity {

    private static DibujoMatriz dibujoMatriz;
    private static ControlTouch controlTouch;
    private static Game game;
    private static Puntuacion puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.controlTouch = new ControlTouch(this);
        this.puntuacion = new Puntuacion(this);
        this.dibujoMatriz = new DibujoMatriz(this, this.puntuacion);
        this.game = new Game();
        this.game.addObserver(this.dibujoMatriz);
        Button newgame = findViewById(R.id.buttonNewgame) ;
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     empezarJuego();
            }
        });
        this.empezarJuego();
    }

    public void empezarJuego(){
        Constantes.MOVIMIENTO = "";
        this.dibujoMatriz.pintarMatriz();
        this.game.iniciarJuego();
    }

   public void gameOver(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Perdiste");
        builder.setMessage("Perdiste!!");
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
       builder.setPositiveButton("Jugar de Nuevo", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               MainActivity.this.empezarJuego();
           }
       });
        builder.show();
    }

}