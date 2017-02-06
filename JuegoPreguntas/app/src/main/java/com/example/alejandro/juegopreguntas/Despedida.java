package com.example.alejandro.juegopreguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Despedida extends AppCompatActivity {

    TextView correctas;
    TextView incorrectas;
    Button botonInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.despedida);

        correctas = (TextView)findViewById(R.id.textCorrectoDespedida);
        incorrectas = (TextView)findViewById(R.id.textoFallosDespedida);

        botonInicio = (Button)findViewById(R.id.botonVolverInicioDespedida);

        correctas.setText("Aciertos: " + Integer.toString(Puntuacion.getCorrectas()));
        incorrectas.setText("Fallos: " + Integer.toString(Puntuacion.getIncorrectas()));

        // Antes de volver al inicio, reiniciamos las preguntas acertadas falladas
        Puntuacion.reiniciarPuntuacion();


        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Despedida.this, ActividadPrincipal.class));
            }

        });

    }


}
