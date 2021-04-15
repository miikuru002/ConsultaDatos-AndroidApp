package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import com.example.finalbd.BusquedaActivity;

public class VheiculosActivity extends AppCompatActivity {

    EditText numero;
    Button btnCargar,btnAtras;
    ListView listaResultado;
    String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vheiculos);

        listaResultado = (ListView) findViewById(R.id.lvLista);
        btnCargar = (Button) findViewById(R.id.btnLoad);
        btnAtras = (Button) findViewById(R.id.btnAtras);
        numero=(EditText)findViewById(R.id.edtV_numero);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusquedaActivity v= new BusquedaActivity();
                listar_vehiculos("http://proyectofinalbd2.000webhostapp.com/servicios/buscar_vehiculo.php?numero="+numero.getText()+"");
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BusquedaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void listar_vehiculos(String URL){

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = response.replace("]["," , ");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        }

        public void CargarListView(JSONArray ja){

            ArrayList<String> lista = new ArrayList<>();

            for(int i=0;i<ja.length();i+=3){
                try {
                    lista.add(ja.getString(i)+" "+ja.getString(i+1)+" "+ja.getString(i+2)+" "+ja.getString(i+3));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
            listaResultado.setAdapter(adaptador);
        }

}