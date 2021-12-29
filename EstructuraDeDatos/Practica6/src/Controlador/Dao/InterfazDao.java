/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Dao;

import Controlador.EstructurasDinamicas.Lista;

/**
 *
 * @author jere_
 * @param <T>
 */
public interface InterfazDao <T>
{
    public boolean guardar(T dato);
    public boolean modificar(T dato, int pos);
    public Lista<T> listar();
    
    
}
