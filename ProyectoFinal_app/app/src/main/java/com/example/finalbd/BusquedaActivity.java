package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BusquedaActivity extends AppCompatActivity {

    EditText edtNumero,edtFecha_Resolucion,edtDni_ruc;
    Button btnBuscar_Numero,btnBuscar_Fecha,btnBuscar_Dni,btnCerrar_sesion,btnVer_vehiculos;
    TextView txtNumero,txtResolucion,txtFecha,txtVigencia,txtSolicitante,txtDni_ruc,txtActa_entrega;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        //para la busqueda
        edtNumero=(EditText)findViewById(R.id.edtNumero);
        edtFecha_Resolucion=(EditText)findViewById(R.id.edtFecha_Resolucion);
        edtDni_ruc=(EditText)findViewById(R.id.edtDni_Ruc);

        btnBuscar_Numero=(Button) findViewById(R.id.btnBuscar_Numero);
        btnBuscar_Fecha=(Button) findViewById(R.id.btnBuscar_Fecha);
        btnBuscar_Dni=(Button) findViewById(R.id.btnBuscar_Dni);
        btnCerrar_sesion=(Button)findViewById(R.id.btnCerrar_sesion);
        btnVer_vehiculos=(Button)findViewById(R.id.btnVer_vehiculos);

        //para la consulta
        txtNumero=(TextView) findViewById(R.id.txtNumero);
        txtResolucion=(TextView) findViewById(R.id.txtResolucion);
        txtFecha=(TextView) findViewById(R.id.txtFecha);
        txtVigencia=(TextView) findViewById(R.id.txtVigencia);
        txtSolicitante=(TextView) findViewById(R.id.txtSolicitante);
        txtDni_ruc=(TextView) findViewById(R.id.txtDni_ruc);
        txtActa_entrega=(TextView) findViewById(R.id.txtActa_entrega);

        //buscar por numero
        btnBuscar_Numero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar("http://proyectofinalbd2.000webhostapp.com/servicios/buscar_numero.php?numero="+edtNumero.getText()+"");
            }
        });

        //buscar por fecha
        btnBuscar_Fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar("http://proyectofinalbd2.000webhostapp.com/servicios/buscar_fecha.php?fecha="+edtFecha_Resolucion.getText()+"");
            }
        });

        //buscar por numero
        btnBuscar_Dni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscar("http://proyectofinalbd2.000webhostapp.com/servicios/buscar_nro_ruc_dni.php?ruc_dni="+edtDni_ruc.getText()+"");
            }
        });

        //boton cerrar sesion
        btnCerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class); //se llama al login
                startActivity(intent);
                //finish();
            }
        });

        //boton mostrar vehiculo
        btnVer_vehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),VheiculosActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buscar(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i); //se asigna los datos de la web service a cada uno de los editext por medio de los campos de la tabla tramites
                        txtNumero.setText(jsonObject.getString("Numero"));
                        txtResolucion.setText(jsonObject.getString("Resolucion_autorizacion"));
                        txtFecha.setText(jsonObject.getString("Fecha_resolucion"));
                        txtVigencia.setText(jsonObject.getString("Vigencia_resolucion"));
                        txtSolicitante.setText(jsonObject.getString("Solicitante"));
                        txtDni_ruc.setText(jsonObject.getString("Nro_ruc_dni"));
                        txtActa_entrega.setText(jsonObject.getString("Acta_entrega"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() { //en caso de que ocurra un error en la conexion
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXIÓN",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }




}