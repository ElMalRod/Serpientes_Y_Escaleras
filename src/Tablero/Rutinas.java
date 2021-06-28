/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Emilio Maldonado
 */
public class Rutinas {

    public static ImageIcon AjustarImagen(String ico, int Ancho, int Alto) {
        ImageIcon tmpIconAux = new ImageIcon(ico);
        //Escalar Imagen
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(Ancho,
                Alto, Image.SCALE_SMOOTH));//SCALE_DEFAULT
        return tmpIcon;
    }
    static Random R = new Random();
    static boolean[] Sexo = {false, false, false, true, false, false,
        false, true, true};

    

    public static String PonCeros(int Valor, int Tamaño) {
        String Texto = Valor + "";
        while (Texto.length() < Tamaño) {
            Texto = "0" + Texto;
        }
        return Texto;
    }

    public static int nextInt(int Valor) {
        return R.nextInt(Valor);
    }

    public static int nextInt(int Ini, int Fin) {
        return R.nextInt(Fin - Ini + 1) + Ini;
    }

   
    }