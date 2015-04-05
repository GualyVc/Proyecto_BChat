package com.example.gualy.bchat;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Cursor cursor;
    private Tabla manager;
    private SimpleCursorAdapter adapter;
    TextView txtdb_email;
    TextView txtdb_passwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DataBase crear
        Db_Users helper = new Db_Users(this);

       /* findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

//        final String[] from = new String[]{manager.FIELD_email};
//        int[] to = new int[]{android.R.id.text1, android.R.id.text2};
//
//        cursor = manager.cargarCursorContactos();
//        adapter = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);




        txtdb_email = (TextView)findViewById(R.id.txtdbEmail);
        txtdb_passwd = (TextView)findViewById(R.id.txtdbPasswd);
        Db_Users objusuarios = new Db_Users(this);
        ArrayList<Tabla> tbl = objusuarios.getUsuariosA();

        Tabla p = tbl.get(0);
        txtdb_email.setText(p.getEmail());
        txtdb_passwd.setText(p.getPassword());




        Button boton = (Button)findViewById(R.id.login_button_ingresar);
        boton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String usuario = ((EditText)findViewById(R.id.login_editText_email)).getText().toString();
                String password = ((EditText)findViewById(R.id.login_editText_password)).getText().toString();
               // String usuario2 =  txtdb_email.setText(p.getEmail());

              //  new BuscarTask().execute();

                if (usuario.equals("a")&& password.equals("b"))
                {
                    Intent nuevoform = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(nuevoform);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Usuario Incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


//    private class BuscarTask extends AsyncTask {
//        //se ejecuta en primer plano
//        @Override
//        protected void onPreExecute() {
//
//            Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_SHORT).show();
//        }
//        //se ejecuta en segundo plano
//        @Override
//        protected Object doInBackground(Object[] params) {
//            cursor = manager.buscarContacto(txtdb_email.getText().toString());
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            adapter.changeCursor(cursor);
//            Toast.makeText(getApplicationContext(),"Finalizando...",Toast.LENGTH_SHORT).show();
//        }
//
//
//    }



}
