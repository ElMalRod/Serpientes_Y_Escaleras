/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;

/**
 *
 * @author Emilio Maldonado
 */
public class Casilla {

    public int noCasilla;       // 1-100
    public char tipoCasilla;    // N, E, S, T
    public int posiciones;      // con E, S valor aleatorio (5-20)

    public Casilla(int noCasilla, char tipoCasilla, int posiciones) {
        this.noCasilla = noCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posiciones = posiciones;
    }

}
