/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import C.Cola;
import Modelo.Estudiante;
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
    private Cola<Estudiante> colaEstudiantes;
    private Cola<Estudiante> colaInvertida;
    private final String CARPETA = "files" + File.separatorChar + Estudiante.class.getSimpleName() + ".obj" ;

    public ControladorEstudiante()
    {
        colaEstudiantes = new Cola(25); 
        colaInvertida = new Cola(25);
    }
    
    public Cola<Estudiante> getColaEstudiantes() {
        return colaEstudiantes;
    }

    public void setColaEstudiantes(Cola<Estudiante> colaEstudiantes) {
        this.colaEstudiantes = colaEstudiantes;
    }

    public Cola<Estudiante> getColaInvertida() {
        return colaInvertida;
    }

    public void setColaInvertida(Cola<Estudiante> colaInvertida) {
        this.colaInvertida = colaInvertida;
    }

    
    public void añadirEstudiante(String n, int e)
    {
        Estudiante estd = new Estudiante(n,e);
        colaEstudiantes.queue(estd);
        
    }
    
    public Cola<Estudiante> invertirSinEstructRept()
    {
        int datosMovidos = 0;
        
        Cola<Estudiante> aux = new Cola(25);
        Cola<Estudiante> aux2 = new Cola(25);
        
        int datosAMover = colaEstudiantes.length();
        
        moverLista5Veces(datosAMover, datosMovidos, aux, aux2);
        datosAMover-=5;
        moverLista5Veces(datosAMover, datosMovidos, aux, aux2);
        datosAMover-=5;
        moverLista5Veces(datosAMover, datosMovidos, aux, aux2);
        datosAMover-=5;
        moverLista5Veces(datosAMover, datosMovidos, aux, aux2);
        datosAMover-=5;
        moverLista5Veces(datosAMover, datosMovidos, aux, aux2);
        
//        Estudiante tmp;
//        
//        while((tmp = aux2.dequeue()) != null)
//        {
//            System.out.println(tmp.getNombre());
//        }

        return aux2;
    }
    
    private void moverLista5Veces(int datosAMover, int datosMovidos, Cola<Estudiante> aux, Cola<Estudiante> aux2)
    {
        moverListas(datosAMover, datosMovidos, aux, aux2);
        datosAMover--;
        moverListas(datosAMover, datosMovidos, aux, aux2);
        datosAMover--;
        moverListas(datosAMover, datosMovidos, aux, aux2);
        datosAMover--;
        moverListas(datosAMover, datosMovidos, aux, aux2);
        datosAMover--;
        moverListas(datosAMover, datosMovidos, aux, aux2);
    }
    
    private void moverListas(int datosAMover, int datosMovidos, Cola<Estudiante> aux, Cola<Estudiante> aux2)
    {
        Estudiante tmp;
        
        moverListaPrincipalASecundaria(datosAMover, datosMovidos, aux, aux2);
        moverListaSecundariaAPrincipal(datosAMover, datosMovidos, aux, aux2); 
        
        if((tmp = aux.dequeue()) != null)
        {
            aux2.queue(tmp); 
        }
         
    }
    
    private void moverListaSecundariaAPrincipal(int datosAMover, int datosMovidos, Cola<Estudiante> aux, Cola<Estudiante> aux2)
    {
        if(datosMovidos < datosAMover - 1)
        {
            colaEstudiantes.queue(aux.dequeue());
            datosMovidos++;
            moverListaSecundariaAPrincipal(datosAMover, datosMovidos, aux, aux2);
        }
    }

    private void moverListaPrincipalASecundaria(int datosAMover, int datosMovidos, Cola<Estudiante> aux, Cola<Estudiante> aux2)
    {
        Estudiante tmp;

        if(datosMovidos < datosAMover)
        {
            tmp = colaEstudiantes.dequeue();
            aux.queue(tmp);
            datosMovidos++;
            moverListaPrincipalASecundaria(datosAMover, datosMovidos, aux, aux2);
        }
        
    }
    
    public boolean guardar()
    {
        try 
        {
            Cola<Estudiante> aux = colaEstudiantes;
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
    
    public Cola<Estudiante>  listarEstudiantes()
    {
        Cola<Estudiante> cola = new Cola(25);
        
        try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            cola =  (Cola<Estudiante>) ois.readObject();
            ois.close();
        } 
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
        
        return cola;
    }
    
    
    
    
}
