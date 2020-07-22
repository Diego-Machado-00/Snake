package com.example.snake;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Puntuacion {
    private TextView puntuacion;
    private MainActivity mainActivity;

    public Puntuacion(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.puntuacion = this.mainActivity.findViewById(R.id.textViewPuntuacion);
    }

}
