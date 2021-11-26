package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    public   Cronometro c=new Cronometro();
    public  Thread t= new Thread();
    TextView tv_Tiempo;
    Button parar;
    Button reiniciar;
    Button iniciar;


    //Metodo que setea los campos al iniciar la vista
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_Tiempo = (TextView)findViewById(R.id.tv_Tiempo);
        parar=(Button) findViewById(R.id.buttonParar);
        reiniciar=(Button) findViewById(R.id.buttonReiniciar);
        iniciar=(Button) findViewById(R.id.buttonIniciar);
        parar.setEnabled(false);
        reiniciar.setEnabled(false);


    }


    //Metodo del observer que aplica los cambios cada vez que se lo notifica desde el Cronometro
    @Override
    public void update(Observable o, Object arg) {
        tv_Tiempo.setText((CharSequence) arg);

    }


    //Metodo para iniciar el cronometro, donde se inicia el hilo y se setea cada boton correctamente
    public void Iniciar(View view){
        c=new Cronometro();
        c.addObserver(this);
        t=new Thread(c);
        t.start();
        iniciar.setText("Contando");
        iniciar.setEnabled(false);
        parar.setEnabled(true);
        reiniciar.setEnabled(true);
    }



    //Metodo para parar el hilo y para setear los botones acorde al estado del cronometro
    public void Parar(View view){
        //Si el hilo no esta parado lo detenine y los botones cambian de valor
        if(!c.getSuspendido().getParado()){
            c.getSuspendido().setParado(true);
            iniciar.setText("Detenido");
            parar.setText("Reanudar");

        //Si el hilo esta parado lo reanuda y los botones cambian de valor
        }else if(c.getSuspendido().getParado()){
            c.getSuspendido().setParado(false);
            iniciar.setText("Contando");
            parar.setText("Parar");
        }

    }


    //Metodo para reiniciar el cronometro donde se interrumpe el hilo y se establecen los botones por defecto de la app
    public void Reiniciar(View view){
        t.interrupt();
        if(t.isInterrupted()){
            c.getSuspendido().setParado(true);
            tv_Tiempo.setText("00:00:00");
            parar.setEnabled(false);
            reiniciar.setEnabled(false);
            iniciar.setText("Iniciar");
            iniciar.setEnabled(true);
        }
    }
}