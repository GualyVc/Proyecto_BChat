package com.example.gualy.bchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by GUALY on 04/04/2015.
 */

//Creando Base de Datos
public class Db_Users extends SQLiteOpenHelper {

    private static final String DB_NAME = "usuarios";
    private static final int SCHEME_VERSION = 1;
    //Permite ejecutar consultas SQL
    private SQLiteDatabase db;


    //Crea la base o la vuelve a abrir
    public Db_Users (Context context){
        super(context, DB_NAME, null, SCHEME_VERSION);
        db = this.getWritableDatabase();
    }


    //Generamos valores
    private ContentValues generarValores(Tabla usuarios){
        ContentValues valores = new ContentValues();
        valores.put(Tabla.FIELD_email,usuarios.getEmail());
        valores.put(Tabla.FIELD_password,usuarios.getPassword());
        return valores;

    }


    //Creamos nuevo metodo y usamos variable db para insertar datos
    public void insertarUsuarios(Tabla usuarios)
    {
        db.insert(Tabla.TABLE_NAME,null,generarValores(usuarios));

    }


    //Consulta para los adivinados
    public ArrayList<Tabla> getUsuariosA()
    {
        ArrayList<Tabla> usuarios = new ArrayList<>();
        String columnas[] = {Tabla.FIELD_ID,Tabla.FIELD_email,Tabla.FIELD_password,Tabla.FIELD_ESTADO};

        //Devuelve consulta de la base de datos y lo guarda en el cursor
        Cursor c = db.query(Tabla.TABLE_NAME,columnas,null,null,null,null,null);

        //Recorre cursor para inserta los elementos en el arraylist
        if (c.moveToFirst())
        {
            do
            {
                Tabla p = new Tabla();
                p.setId(c.getInt(0));
                p.setEmail(c.getString(1));
                p.setPassword(c.getString(2));
                p.setEstado(c.getInt(3));
                usuarios.add(p);
            }while(c.moveToNext());
        }
        return usuarios;

    }


    //Ejecuta con nombre de tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla.CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
