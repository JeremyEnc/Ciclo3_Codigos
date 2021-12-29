/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Dao;

import Controlador.EstructurasDinamicas.Lista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jere_
 * @param <T>
 */
public class AdaptadorDao <T >implements InterfazDao<T>
{
    private String CARPETA = "files" + File.separatorChar;
    private final Class clazz;

    public AdaptadorDao(Class clazz)
    {
        this.clazz = clazz;
        CARPETA+= this.clazz.getSimpleName() + ".obj";
    }

    @Override
    public boolean guardar(T dato)
    {
        try 
        {
            Lista<T> aux = listar();
            aux.add(dato);
            guardarEnFile(aux);
            return true;
        } 
        catch (IOException e)
        {
            return false;
        }
    }

    @Override
    public boolean modificar(T dato, int pos)
    {
        try
        {
            Lista<T> aux = listar();
            aux.replace(dato, pos);
            guardarEnFile(aux);
            return true;
        }
        catch (IOException ex)
        {
            return false;
        } 
    }

    @Override
    public Lista<T> listar()
    {
        Lista<T> lista = new Lista();
        
        try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<T>)ois.readObject();
            ois.close();
        } 
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
        
        return lista;
    }
    
    private void guardarEnFile(Lista<T> aux) throws FileNotFoundException, IOException
    {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA, false));
        oos.writeObject(aux);
        oos.close();

    }
    
}
