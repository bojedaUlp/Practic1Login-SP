package com.example.practico1login_sp.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.practico1login_sp.model.Usuario;
import com.example.practico1login_sp.request.ApiClient;
import com.example.practico1login_sp.ui.login.MainActivity;

public class ViewModelRegistro extends AndroidViewModel {

    private MutableLiveData<Usuario> usuario;
    private Usuario us;
    private Context context;

    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario=new MutableLiveData<Usuario>();
        }
        return usuario;
    }

    public void verificar(int res){
        if(res==1){
            us=ApiClient.leerUs(context);
            usuario.setValue(us);
        }
    }

    public void guardarUsuario(Usuario u){
        ApiClient.guardar(context,u);
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

}
