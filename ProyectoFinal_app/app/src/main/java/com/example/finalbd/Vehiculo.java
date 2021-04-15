package com.example.finalbd;

public class Vehiculo {
    private String placa,ruta;
    private int numero,img_vehiculo;

    public Vehiculo() {
    }

    public Vehiculo(int numero, String placa, String ruta) {
        this.numero = numero;
        this.placa = placa;
        this.ruta = ruta;
        //this.img_vehiculo = img_vehiculo;
    }

    //metodos getter y setter

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    /*@Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", ruta='" + ruta + '\'' +
                ", numero=" + numero +
                '}';
    }*/
}
