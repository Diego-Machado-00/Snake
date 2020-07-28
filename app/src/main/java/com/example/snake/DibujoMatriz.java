package com.example.snake;


import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;

import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class DibujoMatriz implements Observer {
    private static GridLayout gridLayoutMatriz;
    private static TextView textViewMatriz[][];
    private static LinearLayout linearLayoutSnake;
    private MainActivity mainActivity;
    private Puntuacion puntuacion;

    public DibujoMatriz(MainActivity mainActivity, Puntuacion puntuacion){
        this.mainActivity = mainActivity;
        this.puntuacion = puntuacion;
        this.linearLayoutSnake = this.mainActivity.findViewById(R.id.linearLayoutSnake);
    }

    public void pintarMatriz() {
        this.linearLayoutSnake.removeView(this.gridLayoutMatriz);
        this.puntuacion.getScore().setText("0");
        this.puntuacion.getTieJue().setText("00:00:00");
        this.textViewMatriz = new TextView[20][10];
        this.gridLayoutMatriz = new GridLayout(this.mainActivity);
        this.gridLayoutMatriz.setColumnCount(10);
        this.gridLayoutMatriz.setRowCount(20);
        this.gridLayoutMatriz.setPadding(80,20,30,20);
        Display display = this.mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int heigth = size.y;
        for(int i=0; i< 20 ; i++){
            for(int j=0; j< 10; j++){
                this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                this.textViewMatriz[i][j].setWidth((int)(width*0.7/10));
                this.textViewMatriz[i][j].setHeight((int)(heigth*0.7/20));
                this.textViewMatriz[i][j].setBackgroundColor(Color.BLACK);
                this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
            }
        }
        this.linearLayoutSnake.addView(this.gridLayoutMatriz);
    }


    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Object> elemento = (ArrayList<Object>) arg;
        if(elemento.get(0) != null){
            boolean estado = (boolean) elemento.get(0);
            if(!estado){
                if(elemento.size()>1){
                    ArrayList<Point> cuerpo = (ArrayList<Point>) elemento.get(1);
                    Point comida = (Point) elemento.get(2);
                    if(elemento.size()>3){
                        int[] puntoF = (int[]) elemento.get(3);
                        this.textViewMatriz[puntoF[0]][puntoF[1]].setBackgroundColor(Color.BLACK);
                        int punt = (int) elemento.get(4);
                        this.puntuacion.getScore().setText(Integer.toString(punt));
                    }
                    this.textViewMatriz[comida.x][comida.y].setBackgroundColor(Color.RED);
                    for( int i = 0 ; i < cuerpo.size() ; i++ ) {
                        Point p = cuerpo.get(i);
                        if (i == 0) {
                            this.textViewMatriz[p.x][p.y].setBackgroundColor(Color.YELLOW);
                            this.textViewMatriz[p.x][p.y].setText("  |!  |!  ");
                        } else {
                            this.textViewMatriz[p.x][p.y].setBackgroundColor(Color.GREEN);;
                            this.textViewMatriz[p.x][p.y].setText("");
                        }
                    }
                }
            }else{
                this.mainActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainActivity.gameOver();
                    }
                });
            }

        }else{
            int seg = (int) elemento.get(1);
            int min = (int) elemento.get(2);
            int hor = (int) elemento.get(3);

            String timeplay = "";
            timeplay += (hor>=10? Integer.toString(hor): "0"+ Integer.toString(hor));
            timeplay += ":"+(min>=10? Integer.toString(min): "0"+ Integer.toString(min));
            timeplay += ":"+(seg>=10? Integer.toString(seg): "0"+ Integer.toString(seg));

            this.puntuacion.getTieJue().setText(timeplay);
        }

    }


}
