package com.example.cronometro;

import java.io.Console;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Cronometro extends Observable implements Runnable {
    private int horas=0,minutos=0,segundos=0;
    private String tiempo;
    private Observer observer;
    private String nombre;
    private DetenerCronometro detenido=new DetenerCronometro();
    private int x;



    public Cronometro() {
        super();
        this.nombre = nombre;
        this.detenido.setParado(false);
    }

    public DetenerCronometro getSuspendido() {
        return detenido;
    }

    public void setSuspendido(DetenerCronometro detenido) {

        this.detenido = detenido;
    }

    @Override
    public void run() {
        x=0;
        while(!this.detenido.getParado()) {
            ejecutarCronometro(x);
            x++;

            try {
                Thread.sleep(1000);
                this.detenido.removeParado();
            } catch (InterruptedException e) {
               x=0;
            }
        }
    }

    private void ejecutarCronometro(int x) {

        //tiempo="00:00:00";
        this.tiempo=(cambiarFormato(horas)+":"+cambiarFormato(minutos)+":"+cambiarFormato(segundos));
        this.setChanged();
        this.notifyObservers(this.tiempo);
        this.clearChanged();

        segundos++;
        if(segundos>59) {
            segundos=0;
            cambiarFormato(minutos);
            minutos++;
            if(minutos>59) {
                minutos=0;
                cambiarFormato(horas);
                horas++;
            }
        }


    }

    //Con este método cambiamos el formato de los segundos, minutos y horas, estableciendo que si es menos de 10 añadirlo un cero a la izquierda
    public String cambiarFormato(int tipo){
        String result="";
        if(tipo<10){
            result="0"+tipo;
        }else{
            result=""+tipo++;
        }
        return result;
    }







}
