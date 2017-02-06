package com.example.alejandro.juegopreguntas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Resultados extends AppCompatActivity {

    private TextView texAcierto;
    private TextView texFallo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        texAcierto = (TextView) findViewById(R.id.textoCorrecto);
        texFallo = (TextView) findViewById(R.id.textoIncorrecto);

        texAcierto.setText("Preguntas correctas: " + Puntuacion.getCorrectas());
        texFallo.setText("Preguntas incorrectas: " + Puntuacion.getIncorrectas());



    }
}
