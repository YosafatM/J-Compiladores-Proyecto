package Configuracion;

import java.awt.Color;
import java.util.ArrayList;

public class Configuracion {
    private final ArrayList<Linea> lineas;
    private double x;
    private double y;
    private int angulo;
    private Color color;
    
    public Configuracion(){
        x = 0.0;
        y = 0.0;
        lineas = new ArrayList<>();
    }

    public ArrayList<Linea> getLineas() { return lineas; }
    public double getX() { return x; }
    public double getY() { return y; }
    public int getAngulo() { return angulo; }
    public Color getColor() { return color; }

    public void setAngulo(int angulo) { this.angulo = angulo; }
    public void setColor(Color color) { this.color = color; }

    public void setPosicion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void agregarLinea(Linea linea) {
        lineas.add(linea);
    }

    @Override
    public String toString() {
        StringBuilder valor = new StringBuilder();

        for (Linea linea : lineas) valor.append(linea).append(", ");

        valor.append("x:").append(x).append(" y:").append(y).append(" angulo: ").append(angulo);
        return valor.toString();
    }
}
