/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author thera
 */
public class Cesion {
    private int ID, ID2, IDmoto;
    private String cliente1, cliente2, nombre, matricula;
    private Date date = new Date();
    
    public Cesion(int ID, int ID2, String cliente1, String cliente2, int IDmoto, String nombre, String matricula, Date date){
        this.ID = ID;
        this.ID2 = ID2;
        this.IDmoto = IDmoto;
        this.cliente1 = cliente1;
        this.cliente2 = cliente2;
        this.matricula = matricula;
        this.nombre = nombre;
        this.date = date;
    }

    public Cesion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String toString(){
        String s1 = String.format("%03d", ID);
        String s2 = String.format("%03d", ID2);
        return("EL USUARIO CON NUMERO DE SOCIO " + s1 + " LLAMADO " + cliente1 + " CEDE SU MOTO CON ID " + IDmoto + "CON NOMBRE DE LA MOTO " + nombre + " Y CON MATRICULA " + matricula + " AL  USUARIO CON NUMERO DE SOCIO " + s2 + " LLAMADO " + cliente2 + " EL " + date);
    } 
}
