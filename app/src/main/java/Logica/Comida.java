package Logica;

import android.graphics.Point;

public class Comida {
    private Point comida;

    public Point getComida() {
        return this.comida;
    }

    public Comida(Snake snake){
        this.comida = new Point();
        this.nuevaComida(snake);
    }

    public void nuevaComida(Snake snake) {
        do{
            int aux = (int)(Math.random() * 20);
            int aux1 = (int)(Math.random() * 10);
            this.comida.x = aux;
            this.comida.y = aux1;
        }while(esIgual(snake));
    }

    private boolean esIgual(Snake snake) {
        for(int i = 0 ; i < snake.largoSnake(); i++){
            if(snake.getCuerpo().get(i).x == this.comida.x && snake.getCuerpo().get(i).y == this.comida.y){
                return true;
            }
        }
        return false;
    }


}
