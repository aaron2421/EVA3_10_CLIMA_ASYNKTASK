package com.example.eva3_10_clima_asynktask;

public class Clima {
    private int imagen;
    private double temperatura;
    private String ciudad;
    private String clima;

    public Clima(){
        imagen = R.drawable.sunny;
        temperatura = 28.9;
        ciudad = "Chihuahua";
        clima = "Caluroso";
    }

    public Clima(int imagen, double temperatura, String ciudad, String clima) {
        this.imagen = imagen;
        this.temperatura = temperatura;
        this.ciudad = ciudad;
        this.clima = clima;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public int getImagen() {
        return imagen;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getClima() {
        return clima;
    }
}
