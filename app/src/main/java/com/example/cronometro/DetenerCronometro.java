package com.example.cronometro;

public class DetenerCronometro {
    private boolean parado=false;
    //false--->hilo esta corriendo
    //true---> el hilo esta parado

    //llamamos a este metodo y nos devuelve un booleano de los de arriba, en funcion de si es true o false
    public boolean getParado() {
        return parado;
    }

    //Este metodo nos ayudaria a setear un parado es decir para parar el hilo
    public synchronized void setParado(boolean b) {
        this.parado=b;
        notifyAll();
    }

    //Este metodo nos ayudaria a setear un parado a la inversa es decir para continuar el hilo
    public synchronized void removeParado() throws InterruptedException {
        while(this.parado==true) {
            wait();
        }
    }

}
