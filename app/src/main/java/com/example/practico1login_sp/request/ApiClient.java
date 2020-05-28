package com.example.practico1login_sp.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.practico1login_sp.model.Usuario;

public class ApiClient {

    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){

        if(sp==null){
            sp=context.getSharedPreferences("datos",0);
        }
        return sp;
    }

    public static void guardar(Context context, Usuario usuario){

            SharedPreferences sp= conectar(context);
            SharedPreferences.Editor editor=sp.edit();
            editor.putString("nombre",usuario.getNombre());
            editor.putString("apellido",usuario.getApellido());
            editor.putLong("dni",usuario.getDni());
            editor.putString("email",usuario.getEmail());
            editor.putString("clave",usuario.getClave());
            editor.commit();
    }

    public static Usuario leer(Context context, String correo, String pass){
        SharedPreferences sp= conectar(context);
        Usuario usu= null;
        String nombre=sp.getString("nombre","-1");
        String apellido= sp.getString("apellido","-1");
        Long dni= sp.getLong("dni",-1);
        String email= sp.getString("email","-1");
        String clave= sp.getString("clave","-1");

        if(correo.equals(email) && pass.equals(clave)){
            usu= new Usuario(nombre,apellido,dni,email,clave);
        }
        return usu;
    }
    public static Usuario leerUs(Context context){
        SharedPreferences sp = conectar(context);
        String apellido = sp.getString("apellido", "-1");
        String nombre = sp.getString("nombre", "-1");
        Long dni = sp.getLong("dni", -1);
        String email = sp.getString("email", "-1");
        String password = sp.getString("password", "-1");

        Usuario usuario = new Usuario(nombre, apellido, dni, email, password);
        return usuario;
    }
}
