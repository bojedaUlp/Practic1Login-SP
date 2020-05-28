package com.example.practico1login_sp.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.practico1login_sp.model.Usuario;
import com.example.practico1login_sp.request.ApiClient;

public class ViewModelMainActivity extends AndroidViewModel {
    private MutableLiveData<String> msj;
    private MutableLiveData<Usuario> usuario;
    private Context context;

    public ViewModelMainActivity(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }


    public LiveData<String> getMsj(){
        if(msj==null){
            msj= new MutableLiveData<String>();
        }
        return msj;
    }
    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario=new MutableLiveData<Usuario>();
        }
        return usuario;
    }

    public int validar(String email, String pass){
        int op=-1;
        if(email.isEmpty() && pass.isEmpty()){
            msj.setValue("Completar los datos de usuario");
            op=-1;
        }
        else if(email.isEmpty() && !pass.isEmpty()){
            msj.setValue("Ingrese su email");
            op=-1;
        }
        else if(pass.isEmpty() && !email.isEmpty()){
            msj.setValue("Ingrese su contraseña");
            op=-1;
        }
        else{
            msj.setValue("");
            op=0;
        }
       return op;
    }

    public void buscarUsuario(String email, String pass){

      int res= validar(email,pass);
      if(res==0) {
          Usuario us = ApiClient.leer(context, email, pass);
          if (us != null) {
              usuario.setValue(us);
          }else{
              msj.setValue("No se encontro el usuario verifique sus datos");
          }
      }
    }


}
