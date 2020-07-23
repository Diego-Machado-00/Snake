package Controlador;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.snake.MainActivity;

import Util.Constantes;

public class ControlTouch {
    private MainActivity mainActivity;

    public ControlTouch(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        View fs = this.mainActivity.getWindow().getDecorView();
        fs.setOnTouchListener(new View.OnTouchListener(){
            float x1;
            float x2;
            float y1;
            float y2;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    x1 = event.getX();
                    y1 = event.getY();
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    x2 = event.getX();
                    y2 = event.getY();
                    movimiento(x1, x2, y1, y2);
                }
                return false;
            }
        });
    }

    private void movimiento(float x1, float x2, float y1, float y2) {
        float difX = x2-x1;
        float difY = y2-y1;
        String movimiento;
        if(Math.abs(difX) > Math.abs(difY)){
            if(difX > 0){
                movimiento = Constantes.DERECHA;
            }else{
                movimiento = Constantes.IZQUIERDA;
            }
        }else{
            if(difY > 0){
                movimiento = Constantes.ABAJO;
            }else{
                movimiento = Constantes.ARRIBA;
            }
        }
        Toast.makeText(this.mainActivity.getApplicationContext(), movimiento, Toast.LENGTH_SHORT).show();
        this.mainActivity.moverSnake(movimiento);
    }

}
