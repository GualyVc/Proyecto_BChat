package com.example.gualy.bchat;

/**
 * Created by GUALY on 04/04/2015.
 */

//Creando Tabla de la DB
public class Tabla {
    //Creando campos de la tabla
    public static final String TABLE_NAME = "tablausuarios";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_email = "email";
    public static final String FIELD_password = "password";
    public static final String FIELD_ESTADO = "estado";
    public static final String CREATE_DB_TABLE = "create table " + TABLE_NAME + "( " +
                                                 FIELD_ID + " integer primary key autoincrement, " +
                                                 FIELD_email + " text, " +
                                                 FIELD_password + " text, " +
                                                 FIELD_ESTADO + " INTEGER DEFAULT 0"
                                                 + " );";

    //variables para guardar valores (Getters and Setters)
    private int id;
    private String email;
    private String password;
    private int estado;

    //Constructor con algunos parametros
    public Tabla(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Tabla(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
