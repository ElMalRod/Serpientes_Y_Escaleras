/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

import java.util.Scanner;

/**
 *
 * @author emili
 */
public class IngresarDatos {

    private  Scanner sc =  new Scanner(System.in);

    public IngresarDatos(){

    }
    /**
      *  Metodo para el Ingreso de datos de los Jugadores
      */

    public int ingresarEnteros(String mensajeDesplegar){

        System.out.print(mensajeDesplegar + " ");
        int tmp =  sc.nextInt();
        return tmp;
    }
     /**
      *  Metodo para el Ingreso de datos de los Jugadores
      */
    public String ingresarString(String mensajeDesplegar){

        System.out.print(mensajeDesplegar + " ");
        String stringDato = sc.next();
        return stringDato;

    }


}
