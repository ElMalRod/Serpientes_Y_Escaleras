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

/*
Clase en la que se almacenan los nodos de los Jugadores y el Tablero
*/
public class Lista<T> {

    private Nodo<T> Frente;
    private Nodo<T> Fin;
    public T Dr;

    public Lista() {
        Frente = Fin = null;
        Dr = null;
    }

    public Nodo<T> getFrente() {
        return Frente;
    }

    public Nodo<T> getFin() {
        return Fin;
    }

    public boolean InsertaFrente(T Dato) {
        if (Dato == null) {
            return false;
        }
        Nodo<T> Nuevo;
        try {
            Nuevo = new Nodo<T>(Dato);
        } catch (Exception e) {
            return false;
        }
        //primer nodo de la lista dbl
        if (Frente == null) {
            Frente = Fin = Nuevo;
            return true;
        }
        Nuevo.setSig(Frente);
        Frente.setAnt(Nuevo);
        Frente = Nuevo;
        return true;
    }

    public boolean InsertaFin(T Dato) {
        if (Dato == null) {
            return false;
        }
        Nodo<T> Nuevo;
        try {
            Nuevo = new Nodo<T>(Dato);
        } catch (Exception e) {
            return false;
        }
        //primer nodo de la lista dbl
        if (Frente == null) {
            Frente = Fin = Nuevo;
            return true;
        }
        Fin.setSig(Nuevo);
        Nuevo.setAnt(Fin);
        Fin = Nuevo;
        return true;
    }

    public boolean InsertaOrdenado(T Dato) {
        if (Dato == null) {
            return false;
        }
        Nodo<T> Nuevo;
        try {
            Nuevo = new Nodo<T>(Dato);
        } catch (Exception e) {
            return false;
        }
        //primer nodo de la lista dbl
        if (Frente == null) {
            Frente = Fin = Nuevo;
            return true;
        }
        String IdNuevo = Nuevo.Info.toString();
        String IdNodo = Frente.Info.toString();
        if (IdNuevo.compareTo(IdNodo) <= 0) {
            Nuevo.setSig(Frente);
            Frente.setAnt(Nuevo);
            Frente = Nuevo;
            return true;
        }
        IdNodo = Fin.Info.toString();
        if (IdNuevo.compareTo(IdNodo) >= 0) {
            Fin.setSig(Nuevo);
            Nuevo.setAnt(Fin);
            Fin = Nuevo;
            return true;
        }
        // enytre dos nodos
        Nodo<T> Aux = Frente;
        while (IdNuevo.compareTo(Aux.Info.toString()) > 0) {
            Aux = Aux.getSig();
        }
        Nuevo.setAnt(Aux.getAnt());
        Nuevo.setSig(Aux);
        Aux.getAnt().setSig(Nuevo);
        Aux.setAnt(Nuevo);
        return true;
    }

    public boolean Retira(int Posicion) {
        if (Posicion > Length()) {
            return false;
        }
        Nodo<T> Aux = Frente;
        for (int i = 1; i < Posicion; Aux = Aux.getSig(), i++);

        EliminaNodo(Aux);
        return true;
    }

    public boolean Retira(T Dato) {
        String IdDato = Dato.toString();
        Nodo<T> Aux;
        for (Aux = Frente;
                Aux != null && Aux.Info.toString().compareTo(IdDato) != 0;
                Aux = Aux.getSig());
        if (Aux == null) {
            return false;
        }
        EliminaNodo(Aux);
        return true;
    }

    private void EliminaNodo(Nodo<T> Aux) {
        Dr = Aux.Info;
        //Unico nodo
        if (Fin == Frente) {
            Frente = Fin = null;
            return;
        }
        //inicio de la lista
        if (Frente == Aux) {
            Aux.getSig().setAnt(null);
            Frente = Aux.getSig();
            return;
        }
        // final de la lista
        if (Fin == Aux) {
            Fin = Aux.getAnt();
            Fin.setSig(null);
            return;
        }
        //entre dos nodos
        Aux.getAnt().setSig(Aux.getSig());
        Aux.getSig().setAnt(Aux.getAnt());

    }

    public int Length() {
        Nodo<T> Aux;
        int i;
        for (i = 0, Aux = Frente; Aux != null; Aux = Aux.getSig(), i++);

        return i;
    }

    public boolean Busca(T Dato) {

        return true;
    }

}
