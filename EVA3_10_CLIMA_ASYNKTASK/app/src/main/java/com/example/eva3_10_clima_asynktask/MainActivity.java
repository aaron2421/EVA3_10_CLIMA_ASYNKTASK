package com.example.eva3_10_clima_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{
    Clima[] cCiudades;
    ListView listaClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaClima = findViewById(R.id.listClima);
        new ClimaAsync().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,cCiudades[i].getCiudad(),Toast.LENGTH_SHORT).show();
    }


    private class ClimaAsync extends AsyncTask<Void, Void, String >{
        final String ruta = "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... voids) {
            //Conexion
            String resultado = null;
            try {
                URL url = new URL(ruta);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                //Si se acepto la conexion
                if(http.getResponseCode() == HttpURLConnection.HTTP_OK){
                    //leer la respuesta
                    String line;
                    StringBuffer lineas = new StringBuffer();

                    InputStream inputStream = http.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    while ((line = bufferedReader.readLine())!=null){
                        lineas.append(line);
                    }
                    resultado = lineas.toString();
                    return resultado;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s != null){
                //Procesar el JSON

                try {
                    JSONObject jsonClima = new JSONObject(s);
                    JSONArray jsonCiudades = jsonClima.getJSONArray("list");
                    cCiudades = new Clima[jsonCiudades.length()];
                    for (int i=0; i<jsonCiudades.length();i++){

                        //Leer cada ciudad y poner los datos en una lista
                        JSONObject ciudadActual = jsonCiudades.getJSONObject(i);
                        //Datos de la ciudad
                        String ciudad = ciudadActual.getString("name");
                        Double temp = ciudadActual.getJSONObject("main").getDouble("temp");
                        String description = ciudadActual.getJSONArray("weather").getJSONObject(0).getString("description");
                        cCiudades[i] = new Clima(R.drawable.light_rain, temp, ciudad,description);
                    }
                    //Ya con la lista llena se agrega el adaptaador
                    listaClima.setAdapter(new ClimaAdapter(MainActivity.this,
                            R.layout.layout_clima,cCiudades));
                    listaClima.setOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
