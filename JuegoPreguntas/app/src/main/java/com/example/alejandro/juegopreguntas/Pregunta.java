package com.example.alejandro.juegopreguntas;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Pregunta extends AppCompatActivity {

    private String pregunta;
    private String respuesta;
    private String[] respuestasErroneas;
    private int tipo;
    private String recurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Pregunta(String pregunta, String respuesta, String[] respuestasErroneas,int tipo,String recurso){
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.respuestasErroneas = respuestasErroneas;
        this.tipo = tipo;
        this.recurso = recurso;
    }

    public String getPregunta(){
        return pregunta;
    }

    public String getRespuesta(){
        return respuesta;
    }

    public String [] getRespuestasErroneas(){

        String [] error = new String[2];

        return respuestasErroneas;
    }

    public int getTipo(){
        return tipo;
    }

    public String getRecurso(){
        return recurso;
    }
}
