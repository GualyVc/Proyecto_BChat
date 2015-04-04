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


public class splashScreen extends ActionBarActivity {

    private boolean estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        cargarPreferencias();
        if (estado)
        {
            Intent intent = new Intent(splashScreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            ProcesoCarga proceso = new ProcesoCarga();
            proceso.execute();
        }

    }

    private void cargarPreferencias()
    {
        SharedPreferences mispreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        mispreferencias.getBoolean("isLoad",false);
    }

    private void guardarPreferencias(boolean valor)
    {
        SharedPreferences mispreferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        editor.putBoolean("isLoad",valor);
        editor.commit();
    }

    private class ProcesoCarga extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog dialog;
        ArrayList<Tabla> usuarios = new ArrayList<Tabla>(Arrays.asList(
                new Tabla("admin","pass")

        ));
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(splashScreen.this);
            dialog.setTitle("ESTO ES EL TITULO");
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

        @Override
        protected Void doInBackground(Void... params) {
            Db_Users helper =new Db_Users(splashScreen.this);

            for (int i = 0 ; i<usuarios.size();i++)
            {
                Tabla usr = new Tabla();
                usr = usuarios.get(i);
                helper.insertarUsuarios(usr);
            }
            return null;
        }
    }
}
