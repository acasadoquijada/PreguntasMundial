package com.example.alejandro.juegopreguntas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MenuActividad extends AppCompatActivity {

    private Pregunta pregunta;
    private Button [] botonesRespuestas;
    private TextView textoPregunta;
    private TextView textoPreguntaImagen;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_actividad);

        botonesRespuestas = new Button[4];
        botonesRespuestas[0] = (Button) findViewById(R.id.bRespuesta1);
        botonesRespuestas[1] = (Button) findViewById(R.id.bRespuesta2);
        botonesRespuestas[2] = (Button) findViewById(R.id.bRespuesta3);
        botonesRespuestas[3] = (Button) findViewById(R.id.bRespuesta4);

        textoPregunta = (TextView) findViewById(R.id.textoPregunta);
        textoPreguntaImagen = (TextView)findViewById(R.id.textoPreguntaImagen);

        imagen = (ImageView)findViewById(R.id.imagenPregunta);
        // Elegir pregunta

        pregunta = Preguntas.getPreguntaAleatoria();

        // Actualizar los valores

        actualizarDatos(pregunta);

        // Comprobar que pasa cuando pulsas

        botonesRespuestas[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarRespuesta(botonesRespuestas[0].getText().toString());
            }
        });

        botonesRespuestas[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarRespuesta(botonesRespuestas[1].getText().toString());
            }
        });

        botonesRespuestas[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarRespuesta(botonesRespuestas[2].getText().toString());
            }
        });

        botonesRespuestas[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprobarRespuesta(botonesRespuestas[3].getText().toString());
            }
        });

    }

    private void actualizarDatos(Pregunta pregunta){

        // Pongo el texto de la pregunta
        // Tenemos que tener en cuenta el tipo
        // Si es 0 la pregunta es texto, si es 1 tiene una imagen

        if(pregunta.getTipo() == 0){
            textoPregunta.setText(pregunta.getPregunta());
            textoPregunta.setVisibility(View.VISIBLE);

            if(textoPreguntaImagen.getVisibility() == View.VISIBLE){
                textoPreguntaImagen.setVisibility(View.INVISIBLE);
            }

            if(imagen.getVisibility() == View.VISIBLE){
                imagen.setVisibility(View.INVISIBLE);
            }
        }

        else if(pregunta.getTipo() == 1){
            textoPreguntaImagen.setText(pregunta.getPregunta());
            textoPreguntaImagen.setVisibility(View.VISIBLE);

            int drawableResourceId = this.getResources().getIdentifier(pregunta.getRecurso(),
                    "drawable", this.getPackageName());

            imagen.setImageResource(drawableResourceId);

            imagen.setVisibility(View.VISIBLE);
            if(textoPregunta.getVisibility() == View.VISIBLE){
                textoPregunta.setVisibility(View.INVISIBLE);
            }
        }

        // Array con 4 enteros (Desde 0 a 3) mezclados
        ArrayList<Integer> posiciones = new ArrayList<>();

        for (int i=0; i < 4; i++) {
            posiciones.add(i);
        }

        Collections.shuffle(posiciones);

        //Metemos las respuestas incorrectas y la correcta juntas
        String [] respuestasTodas = new String[4];

        respuestasTodas[0] = pregunta.getRespuestasErroneas()[0];
        respuestasTodas[1] = pregunta.getRespuestasErroneas()[1];
        respuestasTodas[2] = pregunta.getRespuestasErroneas()[2];
        respuestasTodas[3] = pregunta.getRespuesta();

        //Con esos indices mezclados, asignamos los botones
        for(int i = 0; i < respuestasTodas.length; i++){
            botonesRespuestas[posiciones.get(i)].setText(respuestasTodas[i]);
        }

    }

    // Comprobamos si la respuesta dada es correcta
    private void comprobarRespuesta(String respuesta){


        // Como vamos a comprobar la respuesta, la eliminamos.

        Preguntas.eliminarPregunta();

        // Es la respuesta correcta
        if(pregunta.getRespuesta().equals(respuesta)){
            System.out.println("Es la correcta");
            reproducirSonidoCorrecto();
            Toast notificacion=Toast.makeText(this,
                    "¡Respuesta correcta!",Toast.LENGTH_LONG);
            notificacion.show();

            // Almacenar datos
            Puntuacion.correcto();

            // Avanzar a una nueva pregunta

            pregunta = Preguntas.getPreguntaAleatoria();

            // Si pregunta es null significa que hemos respondido
            // a todas las preguntas.
            if(pregunta == null){
                startActivity(new Intent(this,Despedida.class));
            }

            // Si no, seguimos adelante
            else{
                actualizarDatos(pregunta);
            }
        }
        // No lo es
        else{
            System.out.println("No lo es");
            reproducirSonidoIncorrecto();
            Toast notificacion=Toast.makeText(this,
                    "¡Respuesta incorrecta!",Toast.LENGTH_LONG);
            notificacion.show();
            Puntuacion.inCorrectas();
            Intent i=new Intent(MenuActividad.this,Fallo.class);
            i.putExtra("respuestaCorrecta", pregunta.getRespuesta());
            startActivity(i);

        }

    }

    private void reproducirSonidoCorrecto(){
        MediaPlayer correcto;

        correcto = MediaPlayer.create(this, R.raw.correctol);

        correcto.start();
    }

    private void reproducirSonidoIncorrecto(){
        MediaPlayer incorrecto;

        incorrecto = MediaPlayer.create(this, R.raw.error);

        incorrecto.start();

    }
}
