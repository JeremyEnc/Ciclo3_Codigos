/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Dao.AdaptadorDao;
import Controlador.EstructurasDinamicas.Lista;
import Modelo.Persona;
import java.time.LocalDate;

/**
 *
 * @author jere_
 */
public class ControladorPersona extends AdaptadorDao<Persona>
{
    private Lista<Persona> listaPersonas;

    public ControladorPersona(Class clazz)
    {
        super(clazz);
    }
    
    public void agregarCliente(String ced, String ape, String nom, String dir, String cor, String tel, LocalDate fn)
    {
        long id = listaPersonas.length() + 1;
        
        Persona persona = new Persona(ced,ape,nom,dir,cor,tel,fn, id);
        listaPersonas.add(persona);
        guardar(persona);
    }

    public Lista<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(Lista<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
}
