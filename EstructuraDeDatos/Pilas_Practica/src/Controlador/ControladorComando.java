/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import C.Pila;
import Modelo.Comando;
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
public class ControladorComando 
{
    private Pila<Comando> pilaComandos;
    private Pila<Comando> pilaComandosInvertida;
    private final String CARPETA = "files" + File.separatorChar + Comando.class.getSimpleName() + ".obj" ;
    private int tamanoPila;

    public ControladorComando(int n)
    {
        tamanoPila = n;
        this.pilaComandos = new Pila(tamanoPila);
    }

    public Pila<Comando> getPilaComandos() {
        return pilaComandos;
    }

    public void setPilaComandos(Pila<Comando> pilaComandos) {
        this.pilaComandos = pilaComandos;
    }

    public Pila<Comando> getPilaComandosInvertida() {
        return pilaComandosInvertida;
    }

    public void setPilaComandosInvertida(Pila<Comando> pilaComandosInvertida) {
        this.pilaComandosInvertida = pilaComandosInvertida;
    } 
    
    public void agregarComando(String com, String fechaEjecucion)
    {
        Comando comando = new Comando(com, fechaEjecucion);
        pilaComandos.push(comando);
    }

    public void borrarPrimerComando()
    {
        Comando cmd;
        
        Pila<Comando> aux = new Pila(tamanoPila);
        
        while((cmd = pilaComandos.pop()) != null)
        {
            aux.push(cmd);
        }
        
        aux.pop();
        
        while((cmd = aux.pop()) != null)
        {
            pilaComandos.push(cmd);
        }

    }
    
    public Pila<Comando> obtenerPilaInvertida()
    {
        Comando cmd;
        
        Pila<Comando> aux = new Pila(tamanoPila);
        Pila<Comando> aux2 = new Pila(tamanoPila);
        
        while((cmd = pilaComandos.pop()) != null)
        {
            aux.push(cmd);
            aux2.push(cmd);
        }
        
        while((cmd = aux.pop()) != null)
        {
            pilaComandos.push(cmd);
        }

        return aux2;

    }
    
    public Comando obtenerUltimoComando()
    {
        Comando temporal = pilaComandos.pop();
        
        pilaComandos.push(temporal);
        
        return temporal;         
    }
    
    public boolean guardar()
    {
        try 
        {
            Pila<Comando> aux = pilaComandos;
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
    
    public Pila<Comando> listarComandos()
    {
        Pila<Comando> pila = new Pila(tamanoPila);
        
        try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CARPETA));
            pila = (Pila<Comando>)ois.readObject();
            ois.close();
        } 
        catch (IOException | ClassNotFoundException e)
        {
            System.out.println(e);
        }
        
        return pila;
    }
    
}
