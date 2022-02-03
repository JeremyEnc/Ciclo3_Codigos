
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.EstructurasDinamicas;

import Modelo.Nodo.Nodo;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jere_
 * @param <T>
 */
public class Lista <T> implements Serializable
{
    private static final long serialVersionUID = -1956285260094164187l;
    private Nodo cabecera;
    private Class clazz;

    public void setClazz(Class clazz) {
        this.clazz = clazz;
        
    }
    
//    Permite verificar si la lista esta vacia
    public boolean isEmpty()
    {
        return(this.cabecera == null);     
    }   
    
//Inserta un nodo en la cabecera de la lista   
    private void insertHeader(T dato)
    {
        Nodo nodo = new Nodo(dato, cabecera);
        cabecera = nodo;
    }
    
    public void add(T dato)
    {
        if(clazz == null)
        {
            setClazz(dato.getClass());
        }
        insert(dato, length());
    }

//Insertar en una posicion en especifico(index)
    public void insert(T dato, int index)
    {
        if(isEmpty() || index <= 0 )
        {
            insertHeader(dato);
        }
        else
        {
            if(index <= length())
            {
                Nodo iterator = cabecera;

                for (int i = 0; i < index - 1; i++)
                {
                    if (iterator.getNodoNext() == null)
                    {
                        break;
                    }

                    iterator = iterator.getNodoNext();
                }

                Nodo tmp = new Nodo(dato, iterator.getNodoNext());
                iterator.setNodoNext(tmp);
            }
            else
            {
                System.out.println("La posicion ingresada es mayor al tamaño del arreglo");
            }    
        }
    }
    
//Remplazar el valor de una posicion en especifico
    public void replace(T dato, int index)
    {
        if(index < length() && index >= 0)
            {
                Nodo iterator = cabecera;

                for (int i = 0; i < index; i++)
                {
                    if (iterator.getNodoNext() == null)
                    {
                        break;
                    }

                    iterator = iterator.getNodoNext();
                }

                iterator.setDato(dato);
            }
            else
            {
                System.out.println("La posicion ingresada no existe");
            }  
    }
    
    public int getIndexOf(T dato)
    {
        Nodo iterator = cabecera;
        int contador = 0;
        
        if(!isEmpty())
        {
            while(iterator.getDato() != dato)
            {
                iterator = iterator.getNodoNext();
                contador++;
                if(iterator == null)
                {
                    System.out.println("El objeto no se encuentra en la lista");
                    return -1;
                }
            }
        }
        
        return contador; 
    }

//Imprime los elementos dentro de la lista    
    public void print()
    {
        Nodo tmp = cabecera;
        
        while(!isEmpty()&& tmp != null)
        {
            System.out.println(tmp.getDato());
            System.out.println("=======");
            tmp = tmp.getNodoNext();
        }
    }

//Retorna el tamaño de la lista    
    public int length()
    {
        int tamano = 0;
        Nodo tmp = cabecera;

        while (!isEmpty()&& tmp != null)
        {
            tmp = tmp.getNodoNext();
            tamano++;
        }

        return tamano;
    }
 
 //Remueve el header actual de la lista
    public T extractHeader()
    {
        T dato = null;
        if(!isEmpty())
        {
            dato = (T) cabecera.getDato();
            cabecera  = cabecera.getNodoNext();
        }
        return dato;
    }

//Retorna el elemento ubicado en la posicion indicada
    public T getByIndex(int pos)
    {
        T dato = null;
        
        if(!isEmpty() && pos >= 0 && pos < length())
        {
            Nodo tmp = cabecera;
            
            for (int i = 0; i < pos; i++)
            {
                tmp = tmp.getNodoNext();
                
                if(tmp == null) break;
            }
            
            if(tmp != null) dato = (T)tmp.getDato();
        }
   
        return dato;
    }
    
    public void remove(int index)
    {
       if(isEmpty() || index <= 0 )
        {
            extractHeader();
        }
        else
        {
            if(index <= length())
            {
                Nodo iterator = cabecera;

                for (int i = 0; i < index - 1; i++)
                {
                    if (iterator.getNodoNext() == null)
                    {
                        break;
                    }

                    iterator = iterator.getNodoNext();
                }

                if(index == length() - 1)
                {
                    iterator.setNodoNext(null);
                }
                else
                {
                    Nodo tmp = new Nodo(iterator.getNodoNext().getNodoNext(), iterator.getNodoNext().getNodoNext().getNodoNext());
                    iterator.setNodoNext(tmp);
                }
                
            }
            else
            {
                System.out.println("La posicion ingresada es mayor al tamaño del arreglo");
            }    
        }
    }
    
    //Metodo para obtener un atributo de la clase asignada a la lista
    private Field getField(String nombre)
    {
        for(Field field: clazz.getDeclaredFields())
        { 
            if(field.getName().equalsIgnoreCase(nombre))
            {
                field.setAccessible(true);
                return field;
            }
        }
        return null;
    }
    
    /*Metodo para ordenar una lista con el metodo de seleccion
      atributo: variable de la clase que se tomara en cuenta
      orden: Orden ascendente o descendente 
    */
    public void sortBySelection(String atributo, int orden)
    {
        int i,j,k;
        Object t;
        int n = length();

        for (i = 0; i < n - 1; i++)
        {
            try
            {
                k = i;
                t = getByIndex(i);
                
                for (j = i + 1; j < n; j++)
                {
                    try 
                    {
                        
                        boolean band = false;
                        Object datoT = getField(atributo).get(t);
                        Object datoJ = getField(atributo).get(getByIndex(j));
                        
                        if (datoT instanceof String)
                        {
                            band = (orden == 1) ? datoT.toString().compareTo(datoJ.toString()) > 0
                                                : datoT.toString().compareTo(datoJ.toString()) < 0;   
                        }
                        else if (datoT instanceof Number)
                        {
                            Number aux = (Number) datoT;
                            Number numero = (Number) datoJ;

                            band = (orden == 1) ? numero.doubleValue() < aux.doubleValue()
                                                : numero.doubleValue() > aux.doubleValue();
                        }
                        
                        if (band)
                        {
                            t = getByIndex(j);
                            k = j;
                        }
                            
                    } 
                    catch (SecurityException ex) 
                    {
                        Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                replace(getByIndex(i), k);
                replace((T) t, i);
                
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sortByInsertion(String atributo, int orden)
    {
        Object t;
        Object aux;
        
        for (int i = 1; i < length(); i++)
        {
            try 
            {
                int j = i - 1;
                boolean band = false;
                t = getByIndex(j);
                aux = getByIndex(i);
             
                if(getField(atributo).get(t) instanceof String)
                {
                    band = true;
                    
                    String auxString = getField(atributo).get(aux).toString();

                    while (band)
                    {
                        band = (orden == 1)? (j >= 0 && auxString.compareTo(getField(atributo).get(getByIndex(j)).toString()) < 0)
                                           : (j >= 0 && auxString.compareTo(getField(atributo).get(getByIndex(j)).toString()) > 0);
                        
                        if (band)
                        {
                            replace(getByIndex(j), j + 1);
                            j--;
                        }
                    }
                }
                else if(getField(atributo).get(t) instanceof Number)
                {
                    band = true;
                    Number auxNumber = (Number)getField(atributo).get(aux);
                    

                    while (band)
                    {
                        band = (orden == 1)? (j >= 0 && auxNumber.doubleValue() < ((Number) getField(atributo).get(getByIndex(j))).doubleValue())
                                            : (j >= 0 && auxNumber.doubleValue() > ((Number) getField(atributo).get(getByIndex(j))).doubleValue());
                        
                        if(band)
                        {
                            replace(getByIndex(j), j + 1);
                            j--;
                        }
                    }
                }
                replace((T) aux, j+1);
 
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sortByShell(String atributo, int orden)
    {
        int salto, i;
        Object aux;
        boolean cambiando;
        boolean band;
  
        for (salto = length() / 2; salto != 0; salto /= 2)
        {
            cambiando = true;
            
            while (cambiando)
            {                                            
                cambiando = false;
                for (i = 0; i + salto < length(); i++)   
                {
                    try 
                    {
                        if(getField(atributo).get(getByIndex(i)) instanceof String)
                        {
                            String t = getField(atributo).get(getByIndex(i)).toString();
                            String tSalto = getField(atributo).get(getByIndex(i + salto)).toString();
                             
                            band = (orden == 1)? (t.compareTo(tSalto) > 0)
                                               : (t.compareTo(tSalto) < 0);
                            
                            if (band)
                            {
                                aux = getByIndex(i + salto);
                                replace(getByIndex(i), i + salto);
                                replace((T) aux, i);
                                cambiando = true;
                            }
                        }
                        else if(getField(atributo).get(getByIndex(i)) instanceof Number)
                        {
                            Number t = (Number)getField(atributo).get(getByIndex(i));
                            Number tSalto = (Number)getField(atributo).get(getByIndex(i + salto));

                            band = (orden == 1) ? (t.doubleValue() > tSalto.doubleValue())
                                                : (t.doubleValue() < tSalto.doubleValue());   
                            
                            if (band)
                            {
                                aux = getByIndex(i + salto);
                                replace(getByIndex(i), i + salto);
                                replace((T) aux, i);
                                cambiando = true;
                            }
                        }
                        
                    } 
                    catch (IllegalArgumentException | IllegalAccessException ex)
                    {
                        Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        }
    }
    
    public void sortByQuickSort(String atributo, int orden, int izq, int der)
    {
        try
        {
            Object t,q;
            Object pivote = getByIndex(izq);
            Object aux;
            
            int i = izq;
            int j = der;
            
            
            while (i < j) 
            {
                boolean band1 = true, band2 = true;
                try
                {
                    while(band1 || band2)
                    {
                        t = getField(atributo).get(getByIndex(i));
                        q = getField(atributo).get(getByIndex(j));

                        if (t instanceof Number)
                        {

                            Number tNumber = (Number) t;
                            Number qNumber = (Number) q;
                            Number pivoteNumber = (Number) getField(atributo).get(pivote);

                            band1 = (orden == 1) ? (tNumber.doubleValue() <= pivoteNumber.doubleValue() && i < j)
                                                 : (tNumber.doubleValue() >= pivoteNumber.doubleValue() && i < j);
                            if (band1) i++;
                               

                            band2 = (orden == 1) ? (qNumber.doubleValue() > pivoteNumber.doubleValue())
                                                 : (qNumber.doubleValue() < pivoteNumber.doubleValue());

                            if (band2)j--;
                        }
                        else if(t instanceof String)
                        {
                            String tString = ((String) t);
                            String qString = q.toString();
                            String pivoteString = getField(atributo).get(pivote).toString();
                            
                            band1 = (orden == 1) ? (tString.compareToIgnoreCase(pivoteString) <= 0 && i < j)
                                                 : (tString.compareToIgnoreCase(pivoteString) >= 0 && i < j);
                            if (band1) i++;
                               

                            band2 = (orden == 1) ? (qString.compareToIgnoreCase(pivoteString) >  0)
                                                 : (qString.compareToIgnoreCase(pivoteString) <  0);

                            if (band2)j--;
                        }
                    }

                    if (i < j)
                    {
                        aux = getByIndex(i);
                        replace(getByIndex(j), i);
                        replace((T) aux, j);
                    }
                }
                catch (IllegalArgumentException | IllegalAccessException ex) 
                {
                    Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            replace(getByIndex(j), izq);
            replace((T) pivote, j);
            
            if (izq < j - 1)
            {
                sortByQuickSort(atributo, orden, izq, j - 1);
            }
            if (j + 1 < der) {
                sortByQuickSort(atributo, orden, j + 1, der);
            }
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para  buscar en la lista de forma lineal y obtener una lista como resultado
    public Lista<T> multipleSearch(String atributo, Object T)
    {
        Lista<T> lista = new Lista();
        
        if(T instanceof String)
        {
            for (int i = 0; i < length(); i++)
            {
                try 
                {
                    if (getField(atributo).get(getByIndex(i)).toString().contains(T.toString()))
                    {
                        lista.add(getByIndex(i));
                    }
                } 
                catch (IllegalArgumentException | IllegalAccessException ex) 
                {
                    Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        else if(T instanceof Number)
        {
            for (int i = 0; i < length(); i++)
            {
                try 
                {
                    if (((Number)(getField(atributo).get(getByIndex(i)))).doubleValue() == ((Number)(T)).doubleValue())
                    {
                        lista.add(getByIndex(i));
                    }
                } 
                catch (IllegalArgumentException | IllegalAccessException ex) 
                {
                    Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
           
        return lista;
    }
    
    
    //*******************PRIMERO SE DEBE ORDENAR LA LISTA**********************
    //Metodo para buscar en la lista de forma binaria y obtener un solo objeto 
    public T uniqueSearch(String atributo, Object T, int der, int izq)
    {
        try
        {
            int medio = (der + izq)/2;
            Object p = null;
            
            if(T instanceof String)
            {
                if (getField(atributo).get(getByIndex(medio)).toString().compareTo(T.toString()) > 0)
                {
                    der = medio - 1;
                    p = uniqueSearch(atributo, T, izq, der);
                }
                else if (getField(atributo).get(getByIndex(medio)).toString().compareTo(T.toString()) < 0)
                {
                    izq = medio + 1;
                    p = uniqueSearch(atributo, T, izq, der);
                } 
                else if (getField(atributo).get(getByIndex(medio)).toString().compareTo(T.toString()) == 0)
                {
                    return getByIndex(medio);
                }
            }
            else if(T instanceof Number)
            {
                if (((Number)(getField(atributo).get(getByIndex(medio)))).doubleValue() > ((Number)(T)).doubleValue())
                {
                    der = medio - 1;
                    p = uniqueSearch(atributo, T, izq, der);
                }
                else if (((Number)(getField(atributo).get(getByIndex(medio)))).doubleValue() < ((Number)(T)).doubleValue())
                {
                    izq = medio + 1;
                    p = uniqueSearch(atributo, T, izq, der);
                } 
                else if (((Number)(getField(atributo).get(getByIndex(medio)))).doubleValue() == ((Number)(T)).doubleValue())
                {
                    return getByIndex(medio);
                }
            }
            
            return (T) p;
            
        } 
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            Logger.getLogger(Lista.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
