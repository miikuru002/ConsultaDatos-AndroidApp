package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegistroActivity extends AppCompatActivity {

    EditText edtR_Usuario,edtR_Nombres,edtR_Apellidos,edtR_Password;
    Button btnR_Registrar,btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtR_Usuario=(EditText)findViewById(R.id.edtR_Usuario);
        edtR_Nombres=(EditText)findViewById(R.id.edtR_Nombres);
        edtR_Apellidos=(EditText)findViewById(R.id.edtR_Apellidos);
        edtR_Password=(EditText)findViewById(R.id.edtR_Password);
        btnR_Registrar=(Button) findViewById(R.id.btnR_Registrar);
        btnRegistrar=(Button) findViewById(R.id.btnRegistrar);

        btnR_Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //registrar_usuario("http://192.168.56.1/ProyectoFinalBD/servicios/registrar_usuario.php");
                registrar_usuario("http://proyectofinalbd2.000webhostapp.com/servicios/registrar_usuario.php");
            }
        });

    }

    private void registrar_usuario(String URL) {//metodo que envia las peticiones al servidor
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() { //mensaje en caso de que exsita algun error
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError { //emtodo getpaarams, para indicar los parametro que se quieren enviar
                Map<String,String> parametros=new HashMap<String, String>();
                parametros.put("usuario",edtR_Usuario.getText().toString());
                parametros.put("nombres",edtR_Nombres.getText().toString());
                parametros.put("apellidos",edtR_Apellidos.getText().toString());
                parametros.put("password",edtR_Password.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}