package com.example.eva3_10_clima_asynktask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClimaAdapter extends ArrayAdapter<Clima> {
    Context contexto;
    int recurso;
    Clima[] clima_ciudades;


    //contexto de la app, resource es el layout, el objeto clima
    public ClimaAdapter(Context context, int resource, Clima[] objects) {
        super(context, resource, objects);
        contexto = context;
        recurso = resource;
        clima_ciudades = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgClima;
        TextView txtCiudad, txtTemperatura, txtClima;

        //CONVERTVIEW ES UNA FILA DE LA LISTA
        if(convertView == null){
            //CREAR NUESTRO LAYOUT
            //INFLATER
            LayoutInflater lInflater = ((Activity)contexto).getLayoutInflater();
            convertView = lInflater.inflate(recurso,parent,false);
        }
        //SACAMOS DE LA CONVERVIEW LOS DATOS QUE NECESITAMOS
        imgClima = convertView.findViewById(R.id.img_Clima);
        txtCiudad = convertView.findViewById(R.id.txt_Ciudad);
        txtTemperatura = convertView.findViewById(R.id.txt_temperatura);
        txtClima = convertView.findViewById(R.id.txt_Clima);
        //LO LLENAMOS
        imgClima.setImageResource(clima_ciudades[position].getImagen());
        txtCiudad.setText(clima_ciudades[position].getCiudad());
        txtTemperatura.setText(clima_ciudades[position].getTemperatura()+ "C");
        txtClima.setText(clima_ciudades[position].getClima());


        return convertView;
    }
}