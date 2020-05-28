package com.example.practico1login_sp.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.practico1login_sp.R;
import com.example.practico1login_sp.model.Usuario;
import com.example.practico1login_sp.ui.login.ViewModelMainActivity;

public class MainRegistroActivity extends AppCompatActivity {

        private EditText etNombre_r,etApellido_r,etDni_r,etEmail_r,etPassword_r;
        private Button btnGuardar;
        private int bandera=0;
        private Usuario us=null;
        private ViewModelRegistro vm;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
        Intent i = getIntent();
        vm.verificar(i.getIntExtra("bandera",0));
        vm=ViewModelProviders.of(this).get(ViewModelRegistro.class);
        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etNombre_r.setText(usuario.getNombre());
                etApellido_r.setText(usuario.getApellido());
                etDni_r.setText(usuario.getDni()+"");
                etEmail_r.setText(usuario.getEmail());
                etPassword_r.setText(usuario.getClave());

            }
        });

    }

    private void inicializar(){
        etNombre_r=findViewById(R.id.etNombre);
        etApellido_r=findViewById(R.id.etApellido);
        etDni_r=findViewById(R.id.etDni);
        etEmail_r=findViewById(R.id.etEmailR);
        etPassword_r=findViewById(R.id.etPassword);
        btnGuardar=findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us=new Usuario();
                us.setNombre(etNombre_r.getText().toString());
                us.setApellido(etApellido_r.getText().toString());
                us.setDni(Long.parseLong(etDni_r.getText().toString()));
                us.setEmail(etEmail_r.getText().toString());
                us.setClave(etPassword_r.getText().toString());
                vm.guardarUsuario(us);
            }
        });

    }

}