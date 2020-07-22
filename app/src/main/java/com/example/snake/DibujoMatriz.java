package com.example.snake;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class DibujoMatriz implements Observer {
    private GridLayout gridLayoutMatriz;
    private TextView textViewMatriz[][];
    private LinearLayout linearLayoutSnake;
    private MainActivity mainActivity;

    public DibujoMatriz(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.textViewMatriz = new TextView[20][10];
        this.gridLayoutMatriz = new GridLayout(this.mainActivity);
        this.gridLayoutMatriz.setColumnCount(10);
        this.gridLayoutMatriz.setRowCount(20);
        this.gridLayoutMatriz.setPadding(20,10,20,10);
        this.linearLayoutSnake = this.mainActivity.findViewById(R.id.linearLayoutSnake);
        Display display = this.mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int heigth = size.y;
        for(int i=0; i< 20 ; i++){
            for(int j=0; j< 10; j++){
                this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                this.textViewMatriz[i][j].setWidth((int)(width*1/10)); this.textViewMatriz[i][j].setHeight((int)(heigth*0.9/20));
                this.textViewMatriz[i][j].setBackgroundColor(Color.GREEN);
                this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
            }
        }
        this.linearLayoutSnake.addView(this.gridLayoutMatriz);
    }
    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Object> elemento = (ArrayList<Object>) arg;
        ArrayList<Point> cuerpo = (ArrayList<Point>) elemento.get(0);
        for( int i = 0 ; i < cuerpo.size() ; i++ ){
            Point p = cuerpo.get(i);
            if(i==0){
                this.textViewMatriz[p.x][p.y].setBackgroundColor(Color.YELLOW);
            }else{
                this.textViewMatriz[p.x][p.y].setBackgroundColor(Color.BLACK);
            }

        }
    }
}
