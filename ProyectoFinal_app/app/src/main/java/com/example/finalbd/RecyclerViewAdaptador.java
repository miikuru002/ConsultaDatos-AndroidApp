package com.example.finalbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> { //actua como puente de comunicacion entre el layout y la funte de info

    private Context context;
    private List<Vehiculo> VehiculoLista; //se crea una variable tipo lsita para almacenar todos los datos mostrados en cada item

    public RecyclerViewAdaptador(Context context, List<Vehiculo> VehiculoLista) {
        this.context = context;
        this.VehiculoLista = VehiculoLista;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView numero, placa, ruta;
        //ImageView img_vehiculo;

        //se crea el constructor de la clase interna
        public ViewHolder(View itemView) {
            super(itemView);
            numero = (TextView) itemView.findViewById(R.id.txtV_numero);
            placa = (TextView) itemView.findViewById(R.id.txtV_placa);
            ruta = (TextView) itemView.findViewById(R.id.txtV_ruta);
            //img_vehiculo = (ImageView) itemView.findViewById(R.id.imgVehiculo);
        }
    }

    @Override
    //este metodo es el encargado de inflar en contenido de un item para la lista (Inflar: hacer uso de un layout para otro layout)
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_item, null);
        return new ViewHolder(view);
    }

    @Override //metodo que realiza las modificaciones e contenido para cada item
    public void onBindViewHolder(ViewHolder holder, int position) {
        Vehiculo vehiculo=VehiculoLista.get(position);

        holder.numero.setText(String.valueOf(vehiculo.getNumero()));
        holder.placa.setText(vehiculo.getPlaca());
        holder.ruta.setText(vehiculo.getRuta());
        //holder.img_vehiculo.setImageResource(vehiculo.getImg_vehiculo());
    }

    @Override
    //metodo que permite determinar al adpatador la cantidad de elementos que se procesaran
    public int getItemCount() {
        return VehiculoLista.size();
    }

}



