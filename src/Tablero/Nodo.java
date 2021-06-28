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
public class Nodo<T> {

    private Nodo<T> Ant;
    public T Info;
    private Nodo<T> Sig;

    public Nodo(T D) {
        Info = D;
        Sig = null;
        Ant = null;
    }

    public Nodo<T> getSig() {
        return Sig;
    }

    public void setSig(Nodo<T> Ap) {
        Sig = Ap;
    }

    public Nodo<T> getAnt() {
        return Ant;
    }

    public void setAnt(Nodo<T> Ap) {
        Ant = Ap;
    }
}
