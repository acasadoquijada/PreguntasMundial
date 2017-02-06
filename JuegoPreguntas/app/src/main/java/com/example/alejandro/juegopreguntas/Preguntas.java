package com.example.alejandro.juegopreguntas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alejandro on 02/02/2017.
 */

public class Preguntas {

    private static ArrayList<Pregunta> preguntas;
    private static ArrayList<Integer> preguntasYaMostradas;
    private static int pos;

    public Preguntas(ArrayList<Pregunta> p){
        preguntas = new ArrayList<>(p);
    }

    public static void setPreguntas(ArrayList<Pregunta> p){
        preguntas = new ArrayList<>(p);
        preguntasYaMostradas = new ArrayList<>();
    }

    public static Pregunta getPreguntaAleatoria(){
        Random rand = new Random();

        // Significa que YA hemos mostrados todas las preguntas
        if(preguntasYaMostradas.size() == preguntas.size()) {

            // Como hemos mostrado todas, preguntasYaMostradas
            // debe volver a inicializarse para futuras partidas

            reiniciarPreguntas();

            return null;
        }

        else{
            do {
                pos = rand.nextInt(preguntas.size());
            }while(preguntasYaMostradas.contains(pos));

            //preguntasYaMostradas.add(pos);
            Pregunta p = preguntas.get(pos);

            return p;
            }
    }

    public static void reiniciarPreguntas(){
        preguntasYaMostradas = new ArrayList<>();
    }


    public static void eliminarPregunta(){
        preguntasYaMostradas.add(pos);
    }
}
