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
public class Cola <T> implements Serializable
{
    private final int size;
    private Nodo header;

    public Cola(int size)
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
    
    public void queue(T dato)
    {
        if(isEmpty())
        {
            header = new Nodo(dato, null);
        }
        else
        { 
            if(isFull())
            {
                System.out.println("Cola llena");
            }
            else
            {
                Nodo iterator = header;

                while (iterator.getNodoNext() != null)
                {
                    iterator = iterator.getNodoNext();
                }

                Nodo tmp = new Nodo(dato, null);
                iterator.setNodoNext(tmp);
            }   
        }
    }
    
    public T dequeue()
    {
        T dato = null;
        if(!isEmpty())
        {
            dato = (T) header.getDato();
            header  = header.getNodoNext();
        }
        return dato;
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
