package Logica;

public class Tiempo {
    private int seg;
    private int min;
    private int hor;

    public int getSeg() {
        return seg;
    }

    public int getMin() {
        return min;
    }

    public int getHor() {
        return hor;
    }

    public Tiempo() {
        this.seg = 0;
        this.min = 0;
        this.hor = 0;
    }

    public void tiempoJuego(){
        if(this.seg != 60){
            this.seg++;
        }else{
            this.seg = 0;
            this.min++;
        }

        if(this.min == 60){
            this.min = 0;
            this.hor++;
        }
    }

}
