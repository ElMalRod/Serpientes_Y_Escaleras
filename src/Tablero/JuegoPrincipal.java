/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablero;

import Jugador.IngresoJugador;
import Jugador.Jugador;
import java.awt.*;
import static java.awt.Frame.NORMAL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingConstants;

/**
 *
 * @author Emilio Maldonado
 */
public class JuegoPrincipal extends JFrame implements ActionListener {

    String[] NombreJugadores = new String[6];
    int[] Ganadas = new int[6];
    int[] Perdidas = new int[6];
    int[] Jugadas = new int[6];
    String Cantidad, CantidadAux;
    int CantidadAux2;
    int CantidadEntera, CantidadTotalJugadores = 2, CantidadImaginaria = 2;
    Jugador[] Arreglo = new Jugador[10];
    final Color[] colorJugador = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.PINK};
    String mensaje;
    int turno, jugadorActual, dados, iterador;
    JPanel informacion;
    JLabel title, lbJugadorActual, lbTurno, lbCasillaActual, lbMensaje, lbDados;
    Tablero panelTablero;
    JButton btnAvanzar, btnReiniciar, btnSalir;
    JLabel icon;

    Nodo<CasillaTablero>[] jugadores;

    /*
     Metodo que inicializa el juego y carga variables
    */
    public JuegoPrincipal() {
        super(" ");
        setSize(675, 600);
        this.setLocationRelativeTo(null);
        setLayout(null);
        NombreJugadores[0] = "Olvier";
        NombreJugadores[1] = "Emilio";
        turno = 1;
        iterador = 0;
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea agregar mas jugadores?");
        if (resp == 0) {
            Cantidad = JOptionPane.showInputDialog("Seleccione jugadores");
            CantidadEntera = Integer.valueOf(Cantidad);
            for (int i = 1; i <= CantidadEntera; i++) {
                NombreJugadores[i + 1] = JOptionPane.showInputDialog("Jugador # " + (i + 2));
            }
            CantidadTotalJugadores += CantidadEntera;
            jugadores = new Nodo[CantidadTotalJugadores];
        }
        if (resp != 0) {
            CantidadAux = JOptionPane.showInputDialog("Ingrese la cantidad de jugadores que jugaran la partida(Max" + NombreJugadores.length + ")");
            CantidadAux2 = Integer.valueOf(CantidadAux);
            jugadores = new Nodo[CantidadAux2];
        }

        title = new JLabel("Serpientes y Escaleras");
        title.setBounds(527, 50, 150, 35);
        title.setForeground(Color.BLUE);
        add(title);
        lbMensaje = new JLabel();
        lbMensaje.setBounds(30, 10, 800, 20);
        add(lbMensaje);

        icon = new JLabel(Rutinas.AjustarImagen("./src/Imagenes/icon.png", 120, 120));
        icon.setBounds(492, 60, 200, 200);
        add(icon);

        informacion = new JPanel(new GridLayout(0, 2));
        informacion.setBounds(525, 310, 180, 50);
//        informacion.setBackground(Color.yellow);
        add(informacion);

        informacion.add(new JLabel("Jugador:   ", SwingConstants.RIGHT));
        informacion.setForeground(Color.BLUE);
        lbJugadorActual = new JLabel(Integer.toString(iterador + 1));
//        lbJugadorActual.setBounds(480, 100, 100, 100);
        informacion.add(lbJugadorActual);

        informacion.add(new JLabel("Dados:   ", SwingConstants.RIGHT));
        lbDados = new JLabel("0");
        informacion.add(lbDados);

        informacion.add(new JLabel("Casilla actual:   ", SwingConstants.RIGHT));
        lbCasillaActual = new JLabel("0");
        informacion.add(lbCasillaActual);

        JPanel panelTurno = new JPanel(new FlowLayout());
//        panelTurno.setBounds(5, 538, 100, 30);
        panelTurno.setBounds(225, 538, 100, 30);
        panelTurno.add(new JLabel("Turno: "));
        lbTurno = new JLabel(Integer.toString(turno));
        panelTurno.add(lbTurno);
        add(panelTurno);
//        informacion.add(lbTurno);

        // Se crea el tablero
        panelTablero = new Tablero();
        panelTablero.setBounds(25, 35, 500, 500);
        add(panelTablero);

        // Se añaden botones de interfaz
        btnAvanzar = new JButton("Avanzar");
        btnAvanzar.setBounds(530, 400, 100, 35);
        btnAvanzar.addActionListener(this);
        add(btnAvanzar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(530, 440, 100, 35);
        btnSalir.addActionListener(this);
        add(btnSalir);

        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        JButton botonPulsado = (JButton) evt.getSource();
        if (botonPulsado == btnAvanzar) {
            tirarDados();
            return;
        }
        if (botonPulsado == btnSalir) {
            System.out.println("Entró Botón Salir");
            System.exit(NORMAL);
            return;
        }
    }
    /*
    Metodo para tirar los dados e indica las posiciones que tiene que avanzar cada jugador
    */
    private void tirarDados() {
        boolean juegoCompletado = false;
        Nodo<CasillaTablero> posicionActual = null;
        Nodo<CasillaTablero> casillaLlegada = null;

        while (true) {
            mensaje = "El Jugador " + NombreJugadores[iterador];
            turno++;
            dados = Rutinas.nextInt(1, 6);

            posicionActual = jugadores[iterador]; // Guardar posicion actual antes de moverse

            // Primer tiro de ese jugador
            if (posicionActual == null) {
                jugadores[iterador] = panelTablero.tablero.getFrente();
                for (int j = 0; j < dados - 1; jugadores[iterador] = jugadores[iterador].getSig(), j++);
//                jugadores[iterador].Info.setBackground(Color.PINK);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = mensaje + (iterador + 1) + " llegó a la casilla " + jugadores[iterador].Info.noCasilla;
                break;
            }

            posicionActual.Info.pintarCasilla(); // Regresa al color original

            // Me posiciono donde va a llegar el jugador
            for (int j = 0; j < dados; j++) {
                if (jugadores[iterador].getSig() == null) { // Se llega al final del tablero
                    // Se regresa
                    for (int k = 0; k < dados - j; jugadores[iterador] = jugadores[iterador].getAnt(), k++);
                    break;
                }
                jugadores[iterador] = jugadores[iterador].getSig();
            }

            casillaLlegada = jugadores[iterador]; // Guardar donde llegó inicialmente, antes de aplicar avance o retroceso
//            casillaLlegada.Info.setBackground(Color.PINK);
            casillaLlegada.Info.setBackground(colorJugador[iterador]);
            mensaje = mensaje + (iterador + 1) + " llegó a la casilla " + jugadores[iterador].Info.noCasilla;

            // Dependiendo si cayó en escalera o serpiente, moverlo.
            if (casillaLlegada.Info.tipoCasilla == 'E') {
                casillaLlegada.Info.setBackground(colorJugador[iterador]);
                update(getGraphics());
                casillaLlegada.Info.setBackground(java.awt.Color.LIGHT_GRAY);
                esperar();
                for (int j = 0; j < casillaLlegada.Info.posiciones; jugadores[iterador] = jugadores[iterador].getSig(), j++);
//                jugadores[iterador].Info.setBackground(Color.BLACK);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = "¡Escalera! " + mensaje + ", que es una escalera, por lo que avanzó a la casilla " + jugadores[iterador].Info.noCasilla;;
            } else if (casillaLlegada.Info.tipoCasilla == 'S') {
                casillaLlegada.Info.setBackground(colorJugador[iterador]);
                update(getGraphics());
                esperar();
                casillaLlegada.Info.setBackground(java.awt.Color.LIGHT_GRAY);
                for (int j = 0; j < casillaLlegada.Info.posiciones; jugadores[iterador] = jugadores[iterador].getAnt(), j++);
//                jugadores[iterador].Info.setBackground(Color.ORANGE);
                jugadores[iterador].Info.setBackground(colorJugador[iterador]);
                mensaje = "¡Serpiente! " + mensaje + ", que es una serpiente, por lo que se regresó a la casilla " + jugadores[iterador].Info.noCasilla;;
            }
            break;

        }

        lbMensaje.setText(mensaje);
        lbDados.setText(Integer.toString(dados));
        lbCasillaActual.setText(Integer.toString(jugadores[iterador].Info.noCasilla));
        lbTurno.setText(Integer.toString(turno));
        lbJugadorActual.setText(Integer.toString(iterador + 1));

//         Se gana el juego
        if (jugadores[iterador].Info.noCasilla == 100) {
//        if (turno == 10) {
            String texto = "¡JUEGO TERMINADO! \nEl ganador es: Jugador " + NombreJugadores[iterador];
            Ganadas[iterador] = +1;
            JOptionPane.showMessageDialog(this, texto);
            JPanel panel = new JPanel();
            for (int i = 0; i < CantidadTotalJugadores; i++) {
                if (i != iterador) {
                    Perdidas[i] += 1;
                    
                    panel.add(new JLabel("|Jugador:" + NombreJugadores[i] + " Partidas Ganadas:"+Ganadas[i] + " Partidas Perdidas:" + Perdidas[i] +" |" ));
                   
                }
            }
             JOptionPane.showMessageDialog(this, panel);
            JOptionPane.showMessageDialog(this, "|Jugador "+ NombreJugadores[iterador] + " Partidas Ganadas: " + Ganadas[iterador] + " Partidas Perdidas: " + Perdidas[iterador]+" |");
        }

        // Ciclo turno jugadores
        iterador = ((iterador + 1) == CantidadTotalJugadores) ? 0 : (iterador + 1);

    }
    /*
    Metodo de Espera para actualizar los movimientos de los juagadores
    */
    private void esperar() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {
            Logger.getLogger(JuegoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
     Metodo que genera las Serpientes 
    */

    private int SerpientesAlAzar() {
        int Serpientes = 0;
        Serpientes = (int) (Math.random() * 7 + 1);
        return Serpientes;
    }
    /*
    Metodo que genera las Escaleras
    */

    private int EscalerasAlAzar() {
        int Escaleras = 0;
        Escaleras = (int) (Math.random() * 7 + 1);
        return Escaleras;
    }
    /*
     Crea una Clase tablero que extiende de Panel para generar los Botones
    */
    private class Tablero extends JPanel {

        Lista<CasillaTablero> tablero;

        private Tablero() {
            setLayout(new GridLayout(10, 10));
            generarTablero();
//            setBackground(Color.WHITE);
            setVisible(true);
        }
        /*
        Metodo para Generar el tablero
        */
        private void generarTablero() {
            // Crear tablero original
            tablero = new Lista<>();
            for (int i = 0; i < 100; tablero.InsertaFin(new CasillaTablero(++i, 'N', 0)));

            // Crear 5 escaleras
            for (int i = 0; i < EscalerasAlAzar(); generarCasilla(tablero, 'E', 15, 70), i++);

            // Crear 5 serpientes
            for (int i = 0; i < SerpientesAlAzar(); generarCasilla(tablero, 'S', 30, 95), i++);

            // TODO: Añadirlos ascendente y descendente, como el juego real
            // Añadir al panel
            Nodo<CasillaTablero> aux = tablero.getFrente();
            for (int i = 0; i < tablero.Length(); i++, aux = aux.getSig()) {
//                aux.Info.cambiarTexto();
                aux.Info.pintarCasilla();
                add(aux.Info);
            }
        }
        /*
          Metodo que genera el tipo de casillas del tablero
        */

        private void generarCasilla(Lista<CasillaTablero> tablero, char tipoCasilla, int limiteInferior, int limiteSuperior) {
            int nodoValido = new Random().nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
            Nodo<CasillaTablero> aux = tablero.getFrente();

            for (int i = 0; i < nodoValido - 1; aux = aux.getSig(), i++); // Me posiciono. Condición -1 porque el getFrente "avanza 1 posición".
            while (aux.Info.tipoCasilla != 'N') {
                nodoValido = new Random().nextInt(limiteSuperior - limiteInferior + 1) + limiteInferior;
                aux = tablero.getFrente();
                for (int i = 0; i < nodoValido; aux = aux.getSig(), i++);
            }
            aux.Info.tipoCasilla = tipoCasilla;

            int posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
            Nodo<CasillaTablero> auxTermina = aux;

            // Con esto hago el mismo método funcionable para generar ya sea Escaleras o Serpientes
            if (tipoCasilla == 'E') {
                for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getSig(), i++);
                while (auxTermina.Info.tipoCasilla != 'N') {
                    posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
                    auxTermina = aux;
                    for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getSig(), i++);
                }
                aux.Info.tipoCasilla = 'E';
                auxTermina.Info.tipoCasilla = '+';
            } else if (tipoCasilla == 'S') {
                for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getAnt(), i++);
                while (auxTermina.Info.tipoCasilla != 'N') {
                    posicionesPorAvanzar = new Random().nextInt(20 - 5 + 1) + 5;
                    auxTermina = aux;
                    for (int i = 0; i < posicionesPorAvanzar; auxTermina = auxTermina.getAnt(), i++);
                }
                aux.Info.tipoCasilla = 'S';
                auxTermina.Info.tipoCasilla = '-';
            }
            aux.Info.posiciones = posicionesPorAvanzar;
//            auxTermina.Info.tipoCasilla = 'T';
        }

    }

    public static void main(String[] args) {
        new JuegoPrincipal();
    }

}
