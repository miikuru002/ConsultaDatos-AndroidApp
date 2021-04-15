package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtUsuario,edtPassword;
    Button btnLogin,btnRegistrar;

    String usuario,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario=findViewById(R.id.edtUsuario);
        edtPassword=findViewById(R.id.edtPassword);
        btnLogin=findViewById(R.id.btnRegistrar_accion);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        //recuperar_preferencias(); //se llama al metodo recuperar_preferencias, cada vez que se inicie la app nos mostrara el ultimo usuario y pasword correctos

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario=edtUsuario.getText().toString();
                password=edtPassword.getText().toString();

                if(!usuario.isEmpty() && !password.isEmpty()){
                    //validar_usuario("http://192.168.56.1/ProyectoFinalBD/servicios/validar_usuario.php");
                    validar_usuario("http://proyectofinalbd2.000webhostapp.com/servicios/validar_usuario.php");
                }else{
                    Toast.makeText(MainActivity.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro=new Intent(MainActivity.this,RegistroActivity.class);
                MainActivity.this.startActivity(registro);
                finish();
            }
        });

    }



    private void validar_usuario(final String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) { //parametro que reacciona si se procesa la peticion
                if(!response.isEmpty()){ //se valida que el response no esté vacio, cuando se hace el login correcto
                    //guardar_preferencias(); //se agrega el metodo guardar_prefencias, de este modo solo cuando el usuario y password existan seran guardados por el metodo
                    Intent intent=new Intent(getApplicationContext(),BusquedaActivity.class);
                    startActivity(intent); //lanza la actividad
                    finish(); //para finalizar la actividad actual y dejar solamente la que se está por abrir
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show(); //si el usuario o contra no existe, mostrara un mensaje
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { //reacciona si no se procesa la peticion
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //metodo getParams, donde se colcan los pararmetros que el servicio solicita para devolver una respuesta
                Map<String,String> parametros=new HashMap<String, String>(); //se crea un obejto map para los parametros
                parametros.put("usuario",usuario);
                parametros.put("password",password); //se pasan los parameros capturados en las cajas de texto
                return parametros; //se retorna la conexion de datos
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);//se crea un obejto de la clase Request
        requestQueue.add(stringRequest); //esto ayuda  a procesar todas las peticiones hechas desde la app
    }

    /*private void guardar_preferencias(){ //metodo que hara que se recuerden los usuarios y contraseñas
        SharedPreferences preferences=getSharedPreferences("preferencias_login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit(); //para guardar o actualziar datos en la preferencia
        editor.putString("usuario",usuario); //mediante el metodo putString se agrega el campo usuario y el valor que este tendra
        editor.putString("password",password);
        editor.putBoolean("sesion",true); //para guardar la sesion, en caso sea correcta
        editor.commit(); //se guardan todos los cambios
    }

    private void recuperar_preferencias(){ //metodo que permite recuperar los datos
        SharedPreferences preferences=getSharedPreferences("preferencias_login",Context.MODE_PRIVATE);
        edtUsuario.setText(preferences.getString("usuario","")); //se asignan las preferencias guardadas dentro de los controles setText del activity
        edtPassword.setText(preferences.getString("password",""));


    }*/
}