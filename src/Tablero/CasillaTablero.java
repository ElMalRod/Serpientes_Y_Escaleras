/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;

import javax.swing.*;
import static javax.swing.SwingConstants.TOP;

/**
 *
 * @author Emilio Maldonado
 */
public class CasillaTablero extends JLabel {

    static final int[] tamañoImagenes = {20, 20};
    static final ImageIcon serpienteFin = Rutinas.AjustarImagen("./src/Imagenes/serpienteFin.png", tamañoImagenes[0], tamañoImagenes[1]);
    static final ImageIcon serpienteInicio = Rutinas.AjustarImagen("./src/Imagenes/serpienteInicio.png", tamañoImagenes[0], tamañoImagenes[1]);
    static final ImageIcon escaleraFin = Rutinas.AjustarImagen("./src/Imagenes/escaleraFin.png", tamañoImagenes[0], tamañoImagenes[1]);

    public int noCasilla;       // 1-100
    public char tipoCasilla;    // N, E, S, T
    public int posiciones;      // con E, S valor aleatorio (5-20)

    public CasillaTablero(int noCasilla, char tipoCasilla, int posiciones) {
        this.noCasilla = noCasilla;
        this.tipoCasilla = tipoCasilla;
        this.posiciones = posiciones;
        setOpaque(true);
        pintarCasilla();
        setBorder(BorderFactory.createBevelBorder(TOP, java.awt.Color.cyan, java.awt.Color.BLUE));
        setText(Integer.toString(noCasilla));
    }

    public void pintarCasilla() {
        switch (tipoCasilla) {
            case 'E':
                setIcon(escaleraFin);
                break;
            case 'S':
                setIcon(serpienteFin);
                break;
            case '+':
                setIcon(escaleraFin);
                break;
            case '-':
                setIcon(serpienteInicio);
                break;
        }
        setBackground(java.awt.Color.GRAY);

    }

}
