package com.example.snake;

import android.widget.TextView;

public class Puntuacion {

    private MainActivity mainActivity;
    private static TextView score;
    private static TextView tiempo;

    public static TextView getScore() {
        return score;
    }

    public static TextView getTieJue() {
        return tiempo;
    }

    public Puntuacion(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.score = this.mainActivity.findViewById(R.id.textViewpuntuacion);
        this.tiempo = this.mainActivity.findViewById(R.id.textViewTiempo);
    }
}
