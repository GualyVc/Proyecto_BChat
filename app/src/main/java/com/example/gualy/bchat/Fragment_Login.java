package com.example.gualy.bchat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by GUALY on 02/04/2015.
 */
public class Fragment_Login extends Fragment {
    //private Button login_boton_ingresar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_login,container);
//
//        login_boton_ingresar = (Button)view.findViewById(R.id.login_button_ingresar).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent (Fragment_Login.this, RegisterActivity.class);
//                startActivity(intent);
//            }
//        });



        return view;
    }


}
