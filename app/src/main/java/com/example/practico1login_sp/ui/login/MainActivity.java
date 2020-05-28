package com.example.practico1login_sp.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.practico1login_sp.R;
import com.example.practico1login_sp.model.Usuario;
import com.example.practico1login_sp.ui.registro.MainRegistroActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etUsuario,etPassword;
    private Button btnLog, btnRegistrar;
    private TextView tvMostrar;
    private ViewModelMainActivity vm;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            inicializar();
          vm = ViewModelProviders.of(this).get(ViewModelMainActivity.class);
          vm.getMsj().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMostrar.setText(s);
            }
        });
          vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Intent i=new Intent(getApplicationContext(), MainRegistroActivity.class);
                i.putExtra("bandera",1);
                startActivity(i);
            }
        });

    }

    private void inicializar(){
        etUsuario=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etClave);
        btnLog=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegistrar);
        tvMostrar=findViewById(R.id.tvMostrar);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.buscarUsuario(etUsuario.getText().toString(),etPassword.getText().toString());
            }
        });
       btnRegistrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(), MainRegistroActivity.class);
               i.putExtra("bandera",0);
               startActivity(i);
           }
       });
    }

}