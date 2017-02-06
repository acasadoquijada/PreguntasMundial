package com.example.alejandro.juegopreguntas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Fallo extends AppCompatActivity {

    Button botonInicio;
    Button botonContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fallo);

        botonInicio = (Button) findViewById(R.id.botonInicio);
        botonContinuar = (Button) findViewById(R.id.botonContinuar);

        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                String respuesta =bundle.getString("respuestaCorrecta");
                Toast notificacion=Toast.makeText(Fallo.this,
                        "La respuesta era: " + respuesta,Toast.LENGTH_LONG);
                notificacion.show();

                // Reiniciamos la puntacion
                Puntuacion.reiniciarPuntuacion();

                // Reiniciamos las preguntas
                Preguntas.reiniciarPreguntas();

                startActivity(new Intent(Fallo.this,ActividadPrincipal.class));
            }
        });

        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}



