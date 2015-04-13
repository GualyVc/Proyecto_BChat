package com.example.gualy.bchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class RegisterActivity extends ActionBarActivity {
    String reg_usuario, reg_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }


    public void clicCrearCuenta(View view)
    {
        reg_usuario = ((EditText)findViewById(R.id.register_editText_email)).getText().toString();
        reg_password = ((EditText)findViewById(R.id.register_editText_passwd)).getText().toString();

        if (!reg_usuario.equals("") && !reg_password.equals(""))
        {
            //Llamamos al proceso carga
            ProcesoCarga proceso = new ProcesoCarga();
            //Ejecutamos el AsyncTask
            proceso.execute();

            Intent nuevoform = new Intent(RegisterActivity.this,ChatRoomActivity.class);
            startActivity(nuevoform);
        }else
        {
            Toast.makeText(getApplicationContext(), "Ingrese un Email y una Contraseña", Toast.LENGTH_SHORT).show();

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                new Tabla(reg_usuario, reg_password)


        ));
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            guardarPreferencias(true);

                Intent intent = new Intent(RegisterActivity.this,PerfilActivity.class);
                startActivity(intent);
                finish();

        }

        //Para recorrer el arraylist de los usuarios
        @Override
        protected Void doInBackground(Void... params) {
            //Instancia la TABLA
            Db_Users helper =new Db_Users(RegisterActivity.this);

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
