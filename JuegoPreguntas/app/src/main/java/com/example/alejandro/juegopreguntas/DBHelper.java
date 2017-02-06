package com.example.alejandro.juegopreguntas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper ;
import android.util.Log;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by alejandro on 02/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper   {

    public static final String NOMBRE_BD = "mudiales.db";
    public static final int VERSION_BD = 2;
    public static final String NOMBRE_TABLA = "preguntas";
    public static final String ID = "id";
    public static final String TIPO = "tipo";
    public static final String PREGUNTA = "pregunta";
    public static final String RESPUESTA_CORRECTA = "respuesta_correcta";
    public static final String RESPUESTA_INCORRECTA_1 = "respuesta_incorrecta_1";
    public static final String RESPUESTA_INCORRECTA_2 = "respuesta_incorrecta_2";
    public static final String RESPUESTA_INCORRECTA_3 = "respuesta_incorrecta_3";
    public static final String RECURSO = "recurso";


    private Context ctx;
    protected SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context,NOMBRE_BD,null,VERSION_BD);
        this.ctx = context;
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("debug", "Creando la Base de Datos");
        db.execSQL("CREATE TABLE " + NOMBRE_TABLA + " (" +
        ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
        TIPO + " INTEGER NOT NULL,"+
        PREGUNTA + " TEXT NOT NULL,"+
        RESPUESTA_CORRECTA + " TEXT NOT NULL,"+
        RESPUESTA_INCORRECTA_1 + " TEXT NOT NULL,"+
        RESPUESTA_INCORRECTA_2 + " TEXT NOT NULL,"+
        RESPUESTA_INCORRECTA_3 + " TEXT NOT NULL,"+
        RECURSO + " TEXT)");

        // Rellenamos BD
        rellenarBD(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("debug", "Actualizando la base de datos desde la version " + oldVersion + " a la version " + newVersion);
    }

    public void rellenarBD(SQLiteDatabase db){
        Log.d("debug", "Rellenando BD");

        Scanner sc = new Scanner( this.ctx.getResources().openRawResource(R.raw.base_de_datos) );
        ContentValues valores = new ContentValues();

        sc.nextLine();

        while(sc.hasNextLine()){
            String[] entrada = sc.nextLine().split(",");

            valores.put(PREGUNTA, entrada[0]);
            valores.put(RESPUESTA_CORRECTA, entrada[1]);
            valores.put(RESPUESTA_INCORRECTA_1, entrada[2]);
            valores.put(RESPUESTA_INCORRECTA_2, entrada[3]);
            valores.put(RESPUESTA_INCORRECTA_3, entrada[4]);
            valores.put(TIPO, entrada[5]);
            valores.put(RECURSO, entrada[6]);

            db.insert(NOMBRE_TABLA,null,valores);

            valores.clear();

        }
    }

    // Obtenemos las preguntas de la BD
    // Esto solo se realiza una vez

    public void getPreguntas(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA, null);

        cursor.moveToFirst();
        ArrayList<Pregunta> preguntas = new ArrayList<>();

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                System.out.println(cursor.getString(cursor.getColumnIndex("respuesta_correcta")));

                String textoPregunta = cursor.getString(cursor.getColumnIndex(PREGUNTA));
                String textoRespuesta = cursor.getString(cursor.getColumnIndex(RESPUESTA_CORRECTA));
                String textoRespInc1 = cursor.getString(cursor.getColumnIndex(RESPUESTA_INCORRECTA_1));
                String textoRespInc2 = cursor.getString(cursor.getColumnIndex(RESPUESTA_INCORRECTA_2));
                String textoRespInc3 = cursor.getString(cursor.getColumnIndex(RESPUESTA_INCORRECTA_3));
                String recursoRepuesta = cursor.getString(cursor.getColumnIndex(RECURSO));

                String [] respuestasIncorrectas = new String[3];
                respuestasIncorrectas[0] = textoRespInc1;
                respuestasIncorrectas[1] = textoRespInc2;
                respuestasIncorrectas[2] = textoRespInc3;
                int tipo = Integer.parseInt(cursor.getString(cursor.getColumnIndex(TIPO)));

                Pregunta p = new Pregunta(textoPregunta,textoRespuesta,respuestasIncorrectas,tipo,recursoRepuesta);

                preguntas.add(p);
                cursor.moveToNext();
            }
        }
        cursor.close();

        Preguntas.setPreguntas(preguntas);

    }
}
