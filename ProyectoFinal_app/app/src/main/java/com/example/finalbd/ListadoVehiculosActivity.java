package com.example.finalbd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListadoVehiculosActivity extends AppCompatActivity {

    RecyclerView recyclerViewVehiculo;
    //List<Vehiculo> VehiculoLista;
    private final List<Vehiculo> VehiculoLista = new ArrayList<>();
    private static final String URL="http://proyectofinalbd2.000webhostapp.com/servicios/buscar_vehiculo.php?numero=16";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_vehiculos);

        recyclerViewVehiculo=(RecyclerView)findViewById(R.id.rclVehiculos);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerViewVehiculo.setLayoutManager(manager);
        recyclerViewVehiculo.setHasFixedSize(true);

        RecyclerViewAdaptador adaptador = new RecyclerViewAdaptador(ListadoVehiculosActivity.this, VehiculoLista);
        recyclerViewVehiculo.setAdapter(adaptador);

        //VehiculoLista=new ArrayList<>(); //se inicializa la lista

        ver_vehiculos();
    }

    public void ver_vehiculos() {

        StringRequest vehiculos = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject vehiculo = array.getJSONObject(i);

                        VehiculoLista.add(new Vehiculo(
                                vehiculo.getInt("Numero"),
                                vehiculo.getString("Nro_placa"),
                                vehiculo.getString("Ruta_autorizada")
                                //R.drawable.transporte
                        ));
                    }


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(vehiculos);
    }
}


