package com.example.gualy.bchat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GUALY on 04/04/2015.
 */
public class Db_Users extends SQLiteOpenHelper {

    private static final String DB_NAME = "usuarios";
    private static final int SCHEME_VERSION = 1;
    private SQLiteDatabase db;

    public Db_Users (Context context){
        super(context, DB_NAME, null, SCHEME_VERSION);
        db = this.getWritableDatabase();
    }

    private ContentValues generarValores(Tabla usuarios){
        ContentValues valores = new ContentValues();
        valores.put(Tabla.FIELD_email,usuarios.getEmail());
        valores.put(Tabla.FIELD_password,usuarios.getPassword());
        return valores;

    }

    public void insertarUsuarios(Tabla usuarios)
    {
        db.insert(Tabla.TABLE_NAME,null,generarValores(usuarios));

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tabla.CREATE_DB_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
