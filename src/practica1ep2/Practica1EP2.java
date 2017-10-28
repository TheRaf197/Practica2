/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1ep2;

import Modelo.Modelo;
import java.util.Scanner;
/**
 *
 * @author Alejandro Llorens Ballester
 */
public class Practica1EP2 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Modelo model;
        String name, s;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        int maximport;
        String opcion;
        //Menu
        System.out.println("Bienvenido al sistema de la asociacion ACAMA.");
        System.out.println("Introduzca el importe maximo por miembro.");
        maximport = sc3.nextInt();
        model = new Modelo(maximport);
        do{
            model.Menu();
            System.out.println("Seleccione una opcion: ");
            opcion = sc1.nextLine();
            switch(opcion){
                case "1":
                    model.RegistrarMiembro();
                break;
                case "2":
                    model.RegistrarMoto(); //Error al introducir id valido pero no incluido en el vector Cilindrada falla con valor no valido precio =
                break;
                case "3":
                    model.RegistrarCesion(); //Error cesion valor no valido y puedo hacer cesiones sin motos ni usuarios
                break;
                case "4":
                    System.out.println(model.MostrarMiembrosYMotos());
                    System.out.println("Pulse cualquier tecla para continuar");
                    s = sc2.nextLine();
                break;
                case "5":
                    System.out.println(model.MostrarMotos());
                    System.out.println("Pulse cualquier tecla para continuar");
                    s = sc2.nextLine();
                break;
                case "6":
                    System.out.println(model.MostrarCesiones());
                    System.out.println("Pulse cualquier tecla para continuar");
                    s = sc2.nextLine();
                break;
                case "7":
                    System.out.println("Intorduce el nombre del fichero que se creara(sin .txt): ");
                    name = sc1.nextLine();
                    model.guardar(name);
                    System.out.println("Cerrando aplicacion");
                    System.exit(0);
                break;
                default:
                    System.out.println("La opcion introducida no es valida");
                break;
            }
        }while(opcion != "7");
    }
}
