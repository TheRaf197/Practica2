/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Clases.Cesion;
import Clases.Cliente;
import Clases.Moto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
/**
 *
 * @author Alejandro Llorens Ballester
 */
public class Modelo {
    Scanner sc;
    Entero a = new Entero();
    private String s = "";
    private int numsocio = 1;
    private int IDmoto = 1;
    private String nombre;
    private String matricula;
    private String CC;
    private String line;
    private String preciocompra;
    private ArrayList<Cliente> clientes;
    private int clienteID, clienteID2, motoID;
    private ArrayList<Cesion> cesiones;
    public Modelo() {
        clientes = new ArrayList<Cliente>();
        cesiones = new ArrayList<Cesion>();
    }
    /*****************************
     * 
     * Funcion para mostrar todas las opciones que contendra nuestro menu
     * 
     */
    public void Menu(){
        System.out.printf("Menu:"+"\n");
        System.out.printf("1.Registrar nuevo miembro"+"\n");
        System.out.printf("2.Registrar una nueva motocicleta"+"\n");
        System.out.printf("3.Registrar una cesion"+"\n");
        System.out.printf("4.Mostrar miembros con motos en posesion"+"\n");
        System.out.printf("5.Mostrar motos"+"\n");
        System.out.printf("6.Mostrar cesiones realizadas"+"\n");
        System.out.printf("7.Salir del programa"+"\n");
        
    }
    /****************************
     * 
     * Funcion para registrar un nuevo miembro.
     * Creamos el nuevo cliente y le asignamos el nombre
     * que el usuario ha elegido, se le asigna el numero 
     * de socio automaticamente 
     * 
     */
    public void RegistrarMiembro(){
        sc = new Scanner(System.in);
        do{
        System.out.printf("Introduzca nombre"+"\n");
        nombre = sc.nextLine();
        }while(!NombreCorrecto(nombre));
        Cliente c = new Cliente(nombre, numsocio, 0);
        numsocio ++;
        clientes.add(c);
        
    }
    /****************************
     * 
     * Funcion para registrar una nueva moto.
     * Creamos la nueva moto siempre y cuando haya al menos un cliente
     * Para ello pedimos a quien va a pertenecer la moto mediante la ID del cliente
     * Posteriormente se pide que se introduzcan los datos de la moto
     * En el caso de que supere el precio total que puede tener un cliente, no se registra
     * En el caso de que no lo supere se añade al vector de motos que tiene ese cliente.
     * 
     */
    public void RegistrarMoto(){

        boolean error = true;
        sc = new Scanner(System.in);
        if(clientes.isEmpty()){
            System.out.println("No hay miembros en la ACAMA");
        }   
        else
        {
            for(int i = 0; i < clientes.size(); i ++){
                System.out.println(clientes.get(i).MostrarCliente());
            }
            do{
                while(error){
                    error = Lectura(error,"Identificate como usuario(Introducir ID): ");
                }
                clienteID = a.entero;
                clienteID = EncontrarCliente(clienteID);
                if(clienteID == -1){
                    System.out.println("El cliente no existe");
                    error = true;
                }
            }while(clienteID == -1);
            do{
                System.out.println("Introduzca el numero de matricula de la moto(La matricula debera estar en mayusculas y solo se podran usar consonantes | NNNNLLL): ");
                matricula = sc.nextLine();
            }while(MatriculaCorrecta(matricula) != true);
            
            System.out.println("Introduzca el nombre de la moto:");
            nombre = sc.nextLine();

            do{
                System.out.println("Introduzca los CC(Cilindrada) de la moto:");
                CC = sc.nextLine();
                if(!NumeroCorrecto(CC))
                    CC = "0";
            }while(Integer.valueOf(CC) < 45 || Integer.valueOf(CC) > 1800);
            
            do{
                System.out.println("Introduzca el precio de la moto:");
                preciocompra = sc.nextLine();
                if(!NumeroCorrecto(preciocompra))
                    preciocompra = "0";
            }while(Float.valueOf(preciocompra) <= 0 || Float.valueOf(preciocompra) > 6000);
                
            if((clientes.get(clienteID).getImportemotos() + Float.valueOf(preciocompra)) <= 6000){
                Moto m = new Moto(clienteID,nombre,matricula,Integer.valueOf(CC),Float.valueOf(preciocompra),getContador());
                contadorIDAumenta();
                clientes.get(clienteID).getMotos().add(m);
                clientes.get(clienteID).setImportemotos(m.getPreciocompra());
            }
            else{
                System.out.println("Al registrar esta moto se supera el maximo posible de 6000 €");
                System.out.println("--CONTINUAR--");
                line = sc.nextLine();
            }
                
        }
        
    }
    /***********************************
     * 
     * Esta funcion registra una cesion entre dos clientes.
     * Para ello primero el cliente se identifica y se busca que si que
     * exista, para posteriormente se identifique el segundo. si existe y ademas
     * no es el mismo que el primero, empezamos a operar.
     * Se muestran todas las motos y se selecciona una para realizar la cesion,
     * con todos los cambios que ello conlleva.
     * 
     */
    public void RegistrarCesion(){
        Moto moto_m = new Moto();  
        Cesion cesion;
        boolean error = true;
        Date date;
        
        if(clientes.size() > 1){
            for(int i = 0; i < clientes.size(); i ++){
                System.out.println(clientes.get(i).MostrarCliente());
            }
            do{
                while(error){
                    error = Lectura(error,"Identificate como usuario(Introducir ID): ");
                }
                clienteID = a.entero;
                clienteID = EncontrarCliente(clienteID);
                if(clienteID == -1){
                    System.out.println("El cliente no existe");
                    error = true;
                }
            }while(clienteID == -1);
            
            for(int i = 0; i < clientes.size(); i ++){
                System.out.println(clientes.get(i).MostrarCliente());
            }
            error = true;
            do{
                while(error){
                    error = Lectura(error,"De que miembro deseas recibir la moto(Introducir ID): ");
                }
                clienteID2 = a.entero;
                clienteID2 = EncontrarCliente(clienteID2);
                if(clienteID2 == -1){
                    System.out.println("El cliente no existe");
                    error = true;
                }
            }while(clienteID2 == -1);
            if(!clientes.get(clienteID2).getMotos().isEmpty()){
                String sa = "1";
                if(clienteID != clienteID2){
                    for(int j = 0; j < clientes.get(clienteID2).getMotos().size(); j ++)
                        System.out.println("ID: " + clientes.get(clienteID2).getMotos().get(j).getID() + " NOMBRE DE LA MOTO:" + clientes.get(clienteID2).getMotos().get(j).getNombre());
                    if(!ClientesSinMotos(clienteID)){
                        if(clientes.get(clienteID2).getMotos().isEmpty())
                            System.out.println("El miembro no tiene ninguna moto");
                        do{
                            if(Integer.valueOf(sa) == -1)
                                System.out.println("La moto seleccionada no la posee el cliente " + clienteID2);
                            System.out.println("Que moto deseas(Pon la ID): ");
                            sa = sc.nextLine();
                        }while(Integer.valueOf(sa) < 0 || !NumeroCorrecto(sa) || clientes.get(clienteID2).EncontrarMoto(Integer.valueOf(sa)) == -1);

                        motoID = clientes.get(clienteID2).EncontrarMoto(Integer.valueOf(sa));

                        if((clientes.get(clienteID).getImportemotos() + clientes.get(clienteID2).getMotos().get(motoID).getPreciocompra()) <= 6000){
                            moto_m = clientes.get(clienteID2).getMotos().get(motoID);
                            clientes.get(clienteID).getMotos().add(moto_m);
                            clientes.get(clienteID2).getMotos().remove(motoID);
                            clientes.get(clienteID2).setImporteMotosCedida(moto_m.getPreciocompra());
                            clientes.get(clienteID).setImportemotos(moto_m.getPreciocompra());
                            date = new Date();
                            date.getTime();
                            cesion = new Cesion(clienteID2 + 1,clienteID + 1,clientes.get(clienteID2).getNombre(),clientes.get(clienteID).getNombre(), motoID,moto_m.getNombre(),moto_m.getMatricula(), date);
                            cesiones.add(cesion);
                            System.out.println("La cesion se ha realizado con exito");
                        }
                        else{
                            System.out.println("El capital total de 6000€ se ha superado");
                            System.out.println("--CONTINUAR--");
                            line = sc.nextLine();
                        }
                    }
                    else{
                        System.out.println("Ningun usuario tiene motos");
                        System.out.println("--CONTINUAR--");
                        line = sc.nextLine();
                    }
                }
                else{
                    System.out.println("El usuario no puede ser el mismo que el registrado");
                    System.out.println("--CONTINUAR--");
                    line = sc.nextLine();
                }
        }
        else{
            System.out.println("Este usuario no tiene ninguna moto en propiedad");
            System.out.println("--CONTINUAR--");
            line = sc.nextLine();
        }    
    }
    else{
        System.out.println("No hay suficientes usuarios en la ACAMA(Minimo 2)");
        System.out.println("--CONTINUAR--");
        line = sc.nextLine();
    }
            
    }
    /********************************
     * 
     * Funcion que muestra todos los miembros con todas las motos
     * 
     */
    public String MostrarMiembrosYMotos(){
        String s = "";
        int acc = 0;
        for(int i = 0; i < clientes.size(); i ++){
            if(!clientes.get(i).getMotos().isEmpty()){
                s = s + clientes.get(i).MostrarCliente();
                s = s + "\n";
                s = s + clientes.get(i).Mostrar();
                s = s + "\n";
                s = s + (" IMPORTE TOTAL: " + clientes.get(i).getImportemotos() + "€");
                s = s + "\n";
                s = s + "\n";
            }
            acc++;
        }
        if(s == "")
            s = s + "No hay miembros";
        return s;
    }
    public boolean ClientesSinMotos(int cliente){
        boolean ok = true;
        for(int i = 0; i <clientes.size(); i ++){
            if(!clientes.get(i).getMotos().isEmpty() && i != cliente)
                ok = false;
        }
        return ok;
    }
    /************************************
     * 
     * Funcion que muestra todas las motos
     * 
     */
    public String MostrarMotos(){
        String s = "";
         String s1; 
        for(int i = 0; i < clientes.size(); i ++){
            s1 = String.format("%03d", clientes.get(i).getNumsocio());
            s = s + ("Socio numero: " + s1 + " ");
            s = s + "\n";
            if(!clientes.get(i).getMotos().isEmpty()){
                s = s + clientes.get(i).Mostrar();
            }
            else
                s = s + "No posee ninguna moto";
            
            s = s + "\n";
        }
        if(s == "")
            s = s + "No hay motos";
        return s;
    }
    public void guardar(String name){
        name = name + ".txt";
        Path path = Paths.get(name);
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter w = Files.newBufferedWriter(path, utf8)){
            w.write("INFORMACION ACERCA DE LOS MIEMBROS Y SUS PERTENENCIAS");
            w.newLine();
            if(!clientes.isEmpty()){
                w.write(MostrarMiembrosYMotos());
                w.newLine();
            }
            else
                System.out.println("NO EXISTEN CLIENTES \n");
            
            w.write("CESIONES REALIZADAS");
            w.newLine();
            if(!cesiones.isEmpty()){ 
                for(int i = 0; i < cesiones.size(); i ++){
                    w.write(cesiones.get(i).toString());
                    w.newLine();
                }
            }
            else
                w.write("NO SE HAN REALIZADO CESIONES \n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void contadorIDAumenta(){
        IDmoto ++;
    }
    
    public int getContador(){
        return IDmoto;
    }
    /*****************************
     * 
     * Funcion que muestra las cesiones
     * 
     */
    public String MostrarCesiones(){
        String s = "";
        for(int i = 0; i < cesiones.size(); i ++){
            s = s + (cesiones.get(i).toString() + "\n");
        }
        if(s == "")
            s = s + "No hay cesiones";
        return s;
    }
    
    public int EncontrarCliente(int ID){
        int IDreal = -1;
        
        for(int i = 0; i < clientes.size(); i ++){
            if(clientes.get(i).getNumsocio() == ID)
                IDreal = i;
        }
        return IDreal;
    }
    
    /**************************************
     * 
     * Funcion para poder efectuar una lectura en bucle en 
     * el caso de que el usuario no introduzca bien la ID
     * 
     */
    public boolean Lectura(boolean error, String s){
        Scanner aux = new Scanner(System.in);
        try{
            System.out.println(s);
            a.entero = aux.nextInt();
            error = false;
        }catch(InputMismatchException ex){
            System.out.println("ID equivocado, prueba de nuevo");
        }
        return error;
    }
    /****************
     * 
     * Clase utilizada para poder efectuar correctamente la funcion lectura
     * 
     */
    public class Entero{
        int entero;

        public Entero() {
            this.entero = -1;
        }
        
    }
    public boolean NombreCorrecto(String s){
        boolean correcto = true;
        if(s.isEmpty())
            correcto = false;
        else
            for(int i = 0; i < s.length(); i++){
                if(Character.isDigit(s.charAt(i)))
                    correcto = false;
            }
        
        return correcto;
    }
    
    public boolean NumeroCorrecto(String s){
        boolean correcto = true;
        if(s.isEmpty())
            correcto = false;
        else
            for(int i = 0; i < s.length(); i++){
                if(!Character.isDigit(s.charAt(i)))
                    correcto = false;
            }
        return correcto;
    }
    
    public boolean MatriculaCorrecta(String s){
        boolean correcto = true;
        if(s.isEmpty())
            correcto = false;
        else if(s.length() == 7)
            for(int i = 0; i < s.length(); i++){
                if(i > 0 && i < 4)
                    if(!Character.isDigit(s.charAt(i)))
                        correcto = false;
                if(i > 3 && i < 7)
                    if(!Character.isLetter(s.charAt(i)) || !Character.isUpperCase(s.charAt(i)) || (s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U'))
                        correcto = false;
            }
        else if(s.length() != 7)
            correcto = false;
        
        return correcto;
    }
}