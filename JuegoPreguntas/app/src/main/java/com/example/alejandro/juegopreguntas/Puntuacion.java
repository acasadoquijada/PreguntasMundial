package com.example.alejandro.juegopreguntas;

/**
 * Created by alejandro on 03/02/2017.
 */

public class Puntuacion {

    private static int correctas = 0;
    private static int incorrectas = 0;

    public Puntuacion(){
        correctas = 0;
        incorrectas = 0;
    }

    public static void correcto(){
        correctas++;
    }

    public static void inCorrectas(){
        incorrectas++;
    }

    public static int getCorrectas(){
        return correctas;
    }

    public static int getIncorrectas(){
        return incorrectas;
    }

    public static void reiniciarPuntuacion(){
        correctas = 0;
        incorrectas = 0;
    }
}
