/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Nodo;

import java.io.Serializable;

/**
 *
 * @author jere_
 * @param <T>
 */
public class Nodo <T> implements Serializable
{   
    private T dato;
    private Nodo nodoNext;

    public Nodo(T dato, Nodo nodoNext)
    {
        this.dato = dato;
        this.nodoNext = nodoNext;
    }

    public Nodo()
    {
        this.dato = null;
        this.nodoNext = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getNodoNext() {
        return nodoNext;
    }

    public void setNodoNext(Nodo nodoNext) {
        this.nodoNext = nodoNext;
    }
    
    
}
