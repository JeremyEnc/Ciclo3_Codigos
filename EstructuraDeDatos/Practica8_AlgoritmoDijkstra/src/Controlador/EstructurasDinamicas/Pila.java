/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EstructurasDinamicas;

import Modelo.Nodo.Nodo;
import java.io.Serializable;

/**
 *
 * @author jere_
 * @param <T>
 */
public class Pila <T> implements Serializable
{
    private final int size;
    private Nodo header;

    public Pila(int size)
    {
        this.size = size;
    }
    
    public boolean isEmpty()
    {
        return(this.header == null);  
    }
    
    public boolean isFull()
    {
        return(length() == this.size);
    }
    
    public void push(T dato)
    {
        if(!isFull())
        {
            Nodo nodoP = new Nodo(dato, header);
            header = nodoP;
        }
        else
        {
            System.out.println("Pila llena");
        }
    }
    
    public T pop()
    {
        T dato = null;
        if(!isEmpty())
        {
            dato = (T) header.getDato();
            header  = header.getNodoNext();
        }
        return dato;
    }
   
    public T top()
    {
        return (T)header.getDato();
    }
    
    public int length()
    {
        int tamano = 0;
        Nodo tmp = header;

        while (!isEmpty()&& tmp != null)
        {
            tmp = tmp.getNodoNext();
            tamano++;
        }

        return tamano;
    }
    
    public void print()
    {
        Nodo tmp = header;
        
        while(!isEmpty()&& tmp != null)
        {
            System.out.println(tmp.getDato());
            System.out.println("=======");
            tmp = tmp.getNodoNext();
        }
    }
}
