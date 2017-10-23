/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Alejandro Llorens Ballester
 */
public class Moto {
    private String nombre,matricula;
    private int ID, CC, propietarioID;
    private float preciocompra;

    public Moto(int propietarioID, String nombre, String matricula, int CC, float preciocompra, int ID) {
        this.nombre = nombre;
        this.ID = ID;
        this.matricula = matricula;
        this.CC = CC;
        this.propietarioID = propietarioID;
        this.preciocompra = preciocompra;
    }

    public Moto() {
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getCC() {
        return CC;
    }

    public float getPreciocompra() {
        return preciocompra;
    }

    public int getPropietarioID() {
        return propietarioID;
    }
    public int getID(){
        return ID;
    }
    
    public String Mostrar(int ID){
        return(" MATRICULA: " + this.matricula + " NOMBRE: " + this.nombre + " CC: " + this.CC + " PRECIO: " + this.preciocompra);
    }
}
