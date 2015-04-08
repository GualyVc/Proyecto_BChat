package com.example.gualy.bchat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;

//Carga una vez y llena el registro de datos
public class splashScreen extends ActionBarActivity {

    private boolean estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Si hemos insertado o no registro
        cargarPreferencias();
        if (estado)
        {
            Intent intent = new Intent(splashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            //Llamamos al proceso carga
            ProcesoCarga proceso = new ProcesoCarga();
            //Ejecutamos el AsyncTask
            proceso.execute();
        }

    }

    private void cargarPreferencias()
    {
        SharedPreferences mispreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        //estado = mispreferencias.getBoolean("isLoad",false);
        mispreferencias.getBoolean("isLoad",false);
    }

    private void guardarPreferencias(boolean valor)
    {
        SharedPreferences mispreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putBoolean("isLoad",valor);
        editor.commit();
    }

    //Se implementa
    //Hilo
    private class ProcesoCarga extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog dialog;
        //INSERTA MAS REGISTROS en el arraylist
        ArrayList<Tabla> usuarios = new ArrayList<Tabla>(Arrays.asList(
                new Tabla("admin","pass"),
                new Tabla("juan","perez")


        ));
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(splashScreen.this);
            dialog.setTitle("INICIALIZANDO");
            dialog.setMessage("INSERTANDO EN BD");
            dialog.show();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            guardarPreferencias(true);
            if(dialog.isShowing())
            {
                dialog.dismiss();
                Intent intent = new Intent(splashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        //Para recorrer el arralist de los usuarios
        @Override
        protected Void doInBackground(Void... params) {
            //Instancia la TABLA
            Db_Users helper =new Db_Users(splashScreen.this);

            for (int i = 0 ; i<usuarios.size();i++)
            {
                Tabla usr = new Tabla();
                usr = usuarios.get(i);
                helper.insertarUsuarios(usr);
                // Da un pequeño retardo para mostrar inicializacion
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
            return null;
        }
    }
}
