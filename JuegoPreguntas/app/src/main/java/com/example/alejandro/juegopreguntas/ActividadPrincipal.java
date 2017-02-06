package com.example.alejandro.juegopreguntas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    private static DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);

        Button bJugar = (Button) findViewById(R.id.botonJugar);
        Button bPuntuacion = (Button) findViewById(R.id.botonPuntuacion);
        Button bOtros = (Button) findViewById(R.id.botonOtros);

        this.deleteDatabase("mudiales.db");


        // Cargamos la BD

        cargarBD();

        bJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActividadPrincipal.this, MenuActividad.class));
            }
        });

        bPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActividadPrincipal.this, Resultados.class));
            }

        });

        bOtros.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.juegos.com/juegos/juegos-de-preguntas"));
                startActivity(myWebLink);
            }
        });

    }

    public void cargarBD(){
        db = new DBHelper(this);
        db.getPreguntas();
    }
}
