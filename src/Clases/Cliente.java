/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Llorens Ballester
 */
public class Cliente {
    private String nombre;
    private int numsocio, nummotos, importemotos;
    public ArrayList<Moto> motos = new ArrayList<Moto>();

    public Cliente(String nombre, int numsocio, int importeTotal) {
        this.nombre = nombre;
        this.numsocio = numsocio;
        this.nummotos = 0;
        this.importemotos = importeTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumsocio() {
        return numsocio;
    }

    public int getNummotos() {
        return nummotos;
    }

    public float getImportemotos() {
        return importemotos;
    }
    
    public void setImportemotos(float importe){
        this.importemotos += importe;
    }
    public void setImporteMotosCedida(float importe){
        this.importemotos -= importe;
    }

    public ArrayList<Moto> getMotos() {
        return motos;
    }
    
    public String Mostrar(){
        String s = "";
        String s1 = String.format("%03d", this.numsocio);
        for(int i = 0; i < motos.size(); i ++){
            s = s + motos.get(i).Mostrar(Integer.valueOf(s1));
            s = s + "\n";
        }   
        return s;
    }
    public String MostrarCliente(){
        String s = String.format("%03d", numsocio);
        return("NUMERO DE SOCIO: " + s + " NOMBRE: " + nombre);
    }
    
    public int EncontrarMoto(int ID){
        int IDreal = -1;
        
        for(int i = 0; i < motos.size(); i ++){
            if(motos.get(i).getID() == ID)
                IDreal = i;
        }
        return IDreal;
    }
}
