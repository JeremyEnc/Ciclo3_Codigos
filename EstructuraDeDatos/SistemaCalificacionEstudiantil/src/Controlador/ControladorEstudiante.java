/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import C.Lista;
import Modelo.Estudiante;
import Modelo.Materia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author jere_
 */
public class ControladorEstudiante 
{
    private Lista<Estudiante> listaEstudiantes;
    private final String CARPETA = "files" + File.separatorChar + Estudiante.class.getSimpleName() + ".obj" ;

    public ControladorEstudiante()
    {
        listaEstudiantes = new Lista();
        
    }
    
    public Lista<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(Lista<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
    
    public void insertarEstudiante(String n, int e, String s, int c)
    {
       Estudiante estudiante = new Estudiante(n,e,s,c);
       listaEstudiantes.add(estudiante);
    }
    
    public void insertarNota(int estudiante, String n, double c)
    {
        Materia materia = new Materia(n,c);
        
        if(listaEstudiantes.getByIndex(estudiante).getListaMaterias() == null)
        {
            listaEstudiantes.getByIndex(estudiante).setListaMaterias(new Lista());
        }
        listaEstudiantes.getByIndex(estudiante).getListaMaterias().add(materia);
    }
    
    public boolean guardar()
    {
        try 
        {
            Lista<Estudiante> aux = listar();
            aux = listaEstudiantes;
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CARPETA, false));
            oos.writeObject(aux);
            oos.close();
            return true;
        } 
        catch (IOException e)
        {
            return false;
        }
    }
    
    public Lista<Estudiante> listar()
    {
        Lista<Estudiante> lista = new Lista();
        
        try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            lista = (Lista<Estudiante>)ois.readObject();
            ois.close();
        } 
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
        
        return lista;
    }
}
