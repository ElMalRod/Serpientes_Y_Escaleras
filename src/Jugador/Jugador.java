/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

/**
 *
 * @author emili
 */
public class Jugador {

    /**
     * Variables que Utilizamos para Jugador
     */
    private int ID;
    private int partidasGanadas = 0;
    private int partidasPerdidas = 0;
    private String nombreJugador;
    private IngresarDatos leerDatos = new IngresarDatos();
    private int contadorJugadores = 0;

    /**
     * Constructor de los Objetos de jugador
     */
    public Jugador(int partidasGanadas, String nombreJugador, int partidasPerdidas) {

        this.partidasGanadas = partidasGanadas;
        this.nombreJugador = nombreJugador;
        this.partidasPerdidas = partidasPerdidas;

    }

    public Jugador() {

    }

    /**
     * Retorna los Objetos de jugador
     */
    public String datosJugador() {

        String tmp = ("Jugador: " + nombreJugador + " Partidas Ganadas: " + partidasGanadas + " Partidas Perdidas: " + partidasPerdidas);
        return tmp;

    }

    /**
     * Getters y Setters del Jugador
     */

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    /**
     * Metodo listado de jugador , Imprime los jugadores ingresados en el Vector
     */
    public void listadoJugadores(Jugador[] listadoJugadores) {

        for (int i = 0; i < listadoJugadores.length; i++) {
            if (listadoJugadores[i] != null) {
                System.out.println((i + 1) + ". " + listadoJugadores[i].datosJugador());
            } else {
                System.out.println(i + " ------Sin Jugador------");
            }
        }

    }

    /**
     * Metodo para Agregar jugador , los agrega a el vector Jugador
     */

   

}
